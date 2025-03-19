package com.example.ai_luna_compose.ui.screen.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ai_luna_compose.R
import com.example.ai_luna_compose.ui.common.TitleBar

enum class ScreenTab {
    Tarot, Chat
}

@Composable
fun MainScreenView() {
    // 현재 선택된 탭 상태 (초기값: Tarot)
    var selectedTab by remember { mutableStateOf(ScreenTab.Tarot) }
    // 알림 상태 (예시: false로 고정)
    var hasUnreadNotification by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        // 전체 배경 이미지
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TitleBar(
                    currentTab = selectedTab,
                    hasUnreadNotification = hasUnreadNotification,
                    onAlarmClick = {
                        // 알림 아이콘 클릭 시 동작 (알림 화면은 미구현)
                    }
                )
            },
            bottomBar = {
                BottomBar(
                    selectedTab = selectedTab,
                    onTabSelected = { selectedTab = it }
                )
            },
            content = { innerPadding ->
                // TopAppBar와 BottomAppBar 사이의 빈 공간
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                )
            }
        )
    }
}

@Composable
fun BottomBar(
    selectedTab: ScreenTab,
    onTabSelected: (ScreenTab) -> Unit
) {
    BottomAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .background(
                shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
                color = MaterialTheme.colorScheme.background
            )
// 높이를 늘려 텍스트가 잘리지 않도록 함.
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Tarot 탭 버튼: 아이콘 + 텍스트를 Column으로 구성
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clickable { onTabSelected(ScreenTab.Tarot) }
                    .padding(8.dp)
            ) {
                val tarotIconRes =
                    if (selectedTab == ScreenTab.Tarot) R.drawable.tarot_tint else R.drawable.tarot_gray
                Image(
                    painter = painterResource(id = tarotIconRes),
                    contentDescription = "Tarot Icon",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Tarot",
                    style = MaterialTheme.typography.labelMedium,
                    color = if (selectedTab == ScreenTab.Tarot) Color.White else Color.Gray
                )
            }
            // Chat 탭 버튼: 아이콘 + 텍스트를 Column으로 구성
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clickable { onTabSelected(ScreenTab.Chat) }
                    .padding(8.dp)
            ) {
                val chatIconRes =
                    if (selectedTab == ScreenTab.Chat) R.drawable.chat_tint else R.drawable.chat_gray
                Image(
                    painter = painterResource(id = chatIconRes),
                    contentDescription = "Chat Icon",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Chat",
                    style = MaterialTheme.typography.labelMedium,
                    color = if (selectedTab == ScreenTab.Chat) Color.White else Color.Gray
                )
            }
        }
    }
}
