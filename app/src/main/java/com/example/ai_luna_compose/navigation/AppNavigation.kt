// MyAppNavigation.kt
package com.example.ai_luna_compose.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ai_luna_compose.ui.common.GlobalDialogData
import com.example.ai_luna_compose.ui.screen.chat.ChatDetailScreenView
import com.example.ai_luna_compose.ui.screen.main.MainScreenView
import com.example.ai_luna_compose.ui.screen.splash.SplashScreenView

object NavRoutes {
    const val SPLASH = "splash"
    const val MAIN = "main"
    const val CHAT_DETAIL = "chatDetail"
}

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun MyAppNavigation() {
    val navController: NavHostController = rememberNavController()
    val globalDialogState = remember { mutableStateOf<GlobalDialogData?>(null) }


    CompositionLocalProvider(LocalNavController provides navController, LocalAlertDialogState provides globalDialogState) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .windowInsetsPadding(insets = WindowInsets.navigationBars)
        ) {
            NavHost(
                navController = navController,
                startDestination = NavRoutes.SPLASH
            ) {
                composable(NavRoutes.SPLASH) {
                    SplashScreenView()
                }
                composable(NavRoutes.MAIN) {
                    MainScreenView()
                }
                composable(NavRoutes.CHAT_DETAIL) {
                    ChatDetailScreenView()
                }
            }




        }
    }
}
