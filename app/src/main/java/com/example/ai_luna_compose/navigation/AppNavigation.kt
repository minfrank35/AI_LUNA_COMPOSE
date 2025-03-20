// MyAppNavigation.kt
package com.example.ai_luna_compose.navigation

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ai_luna_compose.ui.screen.chat.ChatDetailScreenView
import com.example.ai_luna_compose.ui.screen.main.MainScreenView
import com.example.ai_luna_compose.ui.screen.splash.SplashScreenView

object NavRoutes {
    const val SPLASH = "splash"
    const val MAIN = "main"
    const val CHAT_DETAIL = "chatDetail"
}

@Composable
fun MyAppNavigation() {
    val navController: NavHostController = rememberNavController()
    CompositionLocalProvider(LocalNavController provides navController) {
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
