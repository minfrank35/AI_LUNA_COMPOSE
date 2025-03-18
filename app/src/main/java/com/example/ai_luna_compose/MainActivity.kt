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
import com.example.ai_luna_compose.ui.theme.AI_LUNA_COMPOSETheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AI_LUNA_COMPOSETheme {
//                CircularIndicator(
//                    loadingTime = 2000L, // 2초 동안 애니메이션
//                    indicatorColor = Color.Blue,
//                    strokeWidth = 8.dp,
//                    radius = 50, // 원의 반지름 (50dp)
//                    onAnimationFinished = {
//                        // 애니메이션이 끝난 후 실행할 로직
//                        println("애니메이션 완료!")
//                    }
//                ) {
//                    // 원 내부에 표시할 Composable (예: 텍스트)
//                    Text(text = "Loading", color = Color.Black)
//                }
                PreviewCommList()
            }
        }
    }
}