package com.example.ai_luna_compose.ui.common.etc

import android.graphics.BlurMaskFilter
import android.graphics.Paint as AndroidPaint
import android.graphics.RectF
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp

@Composable
fun CircularIndicator(
    loadingTime: Long,
    indicatorColor: Color,
    strokeWidth: Dp,
    radius: Dp, // radius를 Dp 타입으로 변경
    onAnimationFinished: () -> Unit = {},
    content: @Composable () -> Unit
) {
    val colorValue = indicatorColor
    // Animatable을 사용해 0f부터 360f까지 애니메이션 (한 번만 실행)
    val animatedAngle = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        animatedAngle.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = loadingTime.toInt(),
                easing = FastOutSlowInEasing // Ease in ease out 효과 적용
            )
        )
        onAnimationFinished()
    }

    Box(
        modifier = Modifier.size(radius * 2),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.padding(strokeWidth).fillMaxSize()) {
            val strokeWidthPx = strokeWidth.toPx()

            drawIntoCanvas { canvas ->
                val glowPaint = AndroidPaint().apply {
                    color = colorValue.toArgb()
                    style = AndroidPaint.Style.STROKE
                    this.strokeWidth = strokeWidthPx
                    strokeCap = AndroidPaint.Cap.ROUND
                    maskFilter = BlurMaskFilter(15f, BlurMaskFilter.Blur.NORMAL)
                }
                val arcRect = RectF(0f, 0f, size.width, size.height)
                canvas.nativeCanvas.drawArc(
                    arcRect,
                    -90f,
                    animatedAngle.value,
                    false,
                    glowPaint
                )
            }
            drawArc(
                color = colorValue,
                startAngle = -90f,
                sweepAngle = animatedAngle.value,
                useCenter = false,
                style = Stroke(width = strokeWidthPx, cap = StrokeCap.Round)
            )
        }
        content()
    }
}
