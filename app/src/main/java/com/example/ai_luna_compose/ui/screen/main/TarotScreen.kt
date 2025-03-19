package com.example.ai_luna_compose.ui.screen.main

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ai_luna_compose.R
import com.example.ai_luna_compose.ui.common.TitleBar
import com.example.ai_luna_compose.ui.common.TitleBarType
import com.example.ai_luna_compose.ui.common.etc.HorizontalViewPager
import kotlin.math.absoluteValue

// 보간 함수: start와 end 사이를 fraction만큼 보간
private fun lerp(start: Float, stop: Float, fraction: Float): Float {
    return start + (stop - start) * fraction
}

@Composable
fun TarotScreen() {
    // 탭 상태 관리 (초기값: "Love")
    var selectedTab by remember { mutableStateOf("Love") }

    Column(modifier = Modifier.fillMaxSize()) {
        TarotViewPager()
        TarotTitle()
        TarotTab(selectedTab = selectedTab, onTabSelected = { selectedTab = it })
        TarotTabList(selectedTab = selectedTab)
    }
}

@Composable
fun TarotViewPager() {
    // tarot 이미지 리소스 목록 (실제 drawable 이름으로 교체하세요)
    val tarotImages = listOf(R.drawable.banner_luna, R.drawable.banner_couple, R.drawable.banner_money)

    // HorizontalViewPager 호출, 높이를 디자인 기준(예: 163.dp)로 지정
    HorizontalViewPager(
        itemList = tarotImages,
        itemSpacing = 24.dp,
        modifier = Modifier.height(163.dp)
    ) { imageRes ->
        Card(
            modifier = Modifier.fillMaxSize(),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "Tarot Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Composable
fun TarotTitle() {
    // TitleBar를 TEXT_ONLY 타입으로 사용하여 "Tarot" 텍스트만 표시
    TitleBar(
        type = TitleBarType.TEXT_ONLY,
        title = "Tarot"
    )
}

@Composable
fun TarotTab(selectedTab: String, onTabSelected: (String) -> Unit) {
    // 탭 항목 목록
    val tabs = listOf("Love", "Career", "Money")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        tabs.forEach { tab ->
            TarotTabItem(
                text = tab,
                isSelected = tab == selectedTab,
                onClick = { onTabSelected(tab) }
            )
        }
    }
}

@Composable
fun TarotTabItem(text: String, isSelected: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
    val textColor = if (isSelected) Color.White else Color.Gray
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        color = textColor,
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(backgroundColor)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick() }
    )
}

@Composable
fun TarotTabList(selectedTab: String) {
    // 선택된 탭에 따라 다른 타로 질문 목록 표시
    val questions = when (selectedTab) {
        "Love" -> listOf(
            "Do I meet my soulmate?",
            "Will my relationship last?",
            "How can I improve my love life?"
        )
        "Career" -> listOf(
            "Is my career on the right path?",
            "Should I switch jobs?",
            "How can I achieve my professional goals?"
        )
        "Money" -> listOf(
            "Will my finances improve?",
            "Is now a good time to invest?",
            "How can I manage my money better?"
        )
        else -> emptyList()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        questions.forEach { question ->
            Text(
                text = question,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}
