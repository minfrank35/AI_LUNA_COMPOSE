package com.example.ai_luna_compose

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import com.example.ai_luna_compose.navigation.MyAppNavigation
import com.example.ai_luna_compose.ui.theme.AI_LUNA_COMPOSETheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AI_LUNA_COMPOSETheme {
                MyAppNavigation()
            }
        }
    }
}