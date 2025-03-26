package com.example.ai_luna_compose.util

import android.graphics.Rect
import android.os.Build
import android.view.View
import android.view.ViewTreeObserver
import android.view.WindowInsets
import android.view.WindowInsetsAnimation
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

enum class Keyboard {
    Opened, Closed
}

@Composable
fun keyboardAsState(): State<Keyboard> {
    val keyboardState = remember { mutableStateOf(Keyboard.Closed) }
    val view = LocalView.current
    DisposableEffect(view) {
        val onGlobalListener = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = Rect()
            view.getWindowVisibleDisplayFrame(rect)
            val screenHeight = view.rootView.height
            val keypadHeight = screenHeight - rect.bottom
            keyboardState.value = if (keypadHeight > screenHeight * 0.15) {
                Keyboard.Opened
            } else {
                Keyboard.Closed
            }
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(onGlobalListener)

        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(onGlobalListener)
        }
    }

    return keyboardState
}



@RequiresApi(Build.VERSION_CODES.R)
fun setInsetsAnimationCallback(
    view: View,
    onProgressFraction: (Float) -> Unit,
    onAnimationEnd: () -> Unit
) {
    view.setWindowInsetsAnimationCallback(object : WindowInsetsAnimation.Callback(DISPATCH_MODE_STOP) {
        override fun onProgress(
            insets: WindowInsets,
            runningAnimations: MutableList<WindowInsetsAnimation>
        ): WindowInsets {
            // 예시: 키보드(IME)의 바텀 인셋을 가져와서 최대 높이 대비 진행률 계산 (여기서는 500px을 최대 키보드 높이라고 가정)
            val imeInsets = insets.getInsets(WindowInsets.Type.ime())
            val fraction = (imeInsets.bottom / 500f).coerceIn(0f, 1f)
            onProgressFraction(fraction)
            return insets
        }

        override fun onEnd(animation: WindowInsetsAnimation) {
            super.onEnd(animation)
            onAnimationEnd()
        }
    })
}

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun KeyboardAnimationEffect(
    onProgressFraction: (Float) -> Unit,
    onAnimationEnd: () -> Unit
) {
    val view = LocalView.current
    DisposableEffect(view) {
        setInsetsAnimationCallback(view, onProgressFraction, onAnimationEnd)
        onDispose {
            view.setWindowInsetsAnimationCallback(null)
        }
    }
}