package com.example.ai_luna_compose.ui.splash

import android.window.SplashScreenView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.ai_luna_compose.R
import com.example.ai_luna_compose.ui.common.CircularIndicator
import com.example.ai_luna_compose.ui.theme.Typography


@Composable
fun SplashScreenView() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop // 이미지가 화면 전체를 채우도록
        )

        CircularIndicator(
            loadingTime = 2000, indicatorColor = Color.White, strokeWidth = 6.dp, radius = 148.dp, onAnimationFinished = {

            }
        ) {
            CircularIndicatorItem(title = "LUNA", subTitle = "AI Fortune Teller")
        }
    }
}

@Composable
fun CircularIndicatorItem(title: String, subTitle: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = title, style = Typography.headlineLarge
        )
        Text(
            text = subTitle, style = Typography.headlineSmall
        )
    }
}