package com.example.ai_luna_compose.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.material.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ai_luna_compose.R
import com.example.ai_luna_compose.ui.screen.main.ScreenTab

@Composable
fun TitleBar(
    currentTab: ScreenTab,
    hasUnreadNotification: Boolean,
    onAlarmClick: () -> Unit
) {
    TopAppBar(
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        contentPadding = PaddingValues(start = 24.dp, top = 13.dp, end = 24.dp, bottom = 13.dp)
    ) {
        when (currentTab) {
            ScreenTab.Tarot -> {
                // Tarot 탭: 왼쪽에 "Luna" (titleLarge 스타일), 오른쪽에 알림 아이콘
                Text(
                    text = "Luna",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(1f)
                )
                Image(
                    painter = painterResource(
                        id = if (hasUnreadNotification) R.drawable.alarm_on else R.drawable.alarm_off
                    ),
                    contentDescription = "Alarm Icon",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onAlarmClick() }
                )
            }
            ScreenTab.Chat -> {
                // Chat 탭: 왼쪽에 "Chat" (titleLarge 스타일)
                Text(
                    text = "Chat",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}