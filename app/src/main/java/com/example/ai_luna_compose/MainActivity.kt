package com.example.ai_luna_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import com.example.ai_luna_compose.navigation.MyAppNavigation
import com.example.ai_luna_compose.ui.theme.AI_LUNA_COMPOSETheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 전체 시스템 바 영역(edge-to-edge)을 활성화 (상태바는 콘텐츠 뒤로, 내비게이션 바는 포함)
        enableEdgeToEdge()
        setContent {
            AI_LUNA_COMPOSETheme {
                // Box에 navigation bar inset을 적용하여 내비게이션 바 영역은 콘텐츠가 그리지 않도록 함.
                Box(
                    modifier = androidx.compose.ui.Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(WindowInsets.navigationBars)
                ) {
                    MyAppNavigation()
                }
            }
        }
    }
}