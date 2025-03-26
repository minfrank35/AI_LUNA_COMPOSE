package com.example.ai_luna_compose

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.core.view.WindowCompat
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowInsetsControllerCompat
import com.example.ai_luna_compose.navigation.MyAppNavigation
import com.example.ai_luna_compose.ui.theme.AI_LUNA_COMPOSETheme
import com.example.ai_luna_compose.ui.theme.BlackTrans
import com.example.ai_luna_compose.ui.theme.SystemColor
import com.google.ar.core.ArCoreApk

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 현재 WindowInsetsController 가져오기
        enableEdgeToEdge(navigationBarStyle = SystemBarStyle.light(SystemColor.toArgb(), SystemColor.toArgb()))
        WindowInsetsControllerCompat(window, window.decorView).run {
            isAppearanceLightStatusBars = false /** 검은색: true, 흰색: false **/
            isAppearanceLightNavigationBars = false /** 검은색: true, 흰색: false **/
        }
        setContent {
            AI_LUNA_COMPOSETheme {
                // Box에 navigation bar inset을 적용하여 내비게이션 바 영역은 콘텐츠가 그리지 않도록 함.
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black)
                        .windowInsetsPadding(insets = WindowInsets.navigationBars)
                ) {
                    MyAppNavigation()
                }
            }
        }
    }
}