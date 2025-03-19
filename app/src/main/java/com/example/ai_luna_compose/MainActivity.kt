package com.example.ai_luna_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ai_luna_compose.ui.common.CommList
import com.example.ai_luna_compose.ui.common.PreviewCommList
import com.example.ai_luna_compose.ui.common.SampleHorizontalViewPager
import com.example.ai_luna_compose.ui.splash.SplashScreenView
import com.example.ai_luna_compose.ui.theme.AI_LUNA_COMPOSETheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AI_LUNA_COMPOSETheme {
                SplashScreenView()
            }
        }
    }
}