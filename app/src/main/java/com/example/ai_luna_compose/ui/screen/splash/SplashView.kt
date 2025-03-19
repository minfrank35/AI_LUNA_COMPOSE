package com.example.ai_luna_compose.ui.screen.splash

import androidx.compose.foundation.Image
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.ai_luna_compose.R
import com.example.ai_luna_compose.navigation.LocalNavController
import com.example.ai_luna_compose.navigation.NavRoutes
import com.example.ai_luna_compose.ui.common.etc.CircularIndicator
import com.example.ai_luna_compose.ui.theme.Typography

@Composable
fun SplashScreenView() {
    // CompositionLocal을 통해 navController에 접근
    val navController = LocalNavController.current

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        // 전체 배경 이미지 (화면 전체에 꽉 채움)
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // CircularIndicator: 애니메이션 종료 시 MainScreenView로 이동
        CircularIndicator(
            loadingTime = 100,
            indicatorColor = Color.White,
            strokeWidth = 6.dp,
            radius = 148.dp,
            onAnimationFinished = {
                navController.navigate(NavRoutes.MAIN) {
                    popUpTo(NavRoutes.SPLASH) { inclusive = true }
                }
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
            text = title,
            style = Typography.headlineLarge
        )
        Text(
            text = subTitle,
            style = Typography.headlineSmall
        )
    }
}
