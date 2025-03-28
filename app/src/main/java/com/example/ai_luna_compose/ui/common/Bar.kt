package com.example.ai_luna_compose.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ai_luna_compose.R
import com.example.ai_luna_compose.navigation.LocalNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class TitleBarType {
    TEXT_ONLY,
    TEXT_AND_ICON,
    BACK_AND_IMAGE_AND_TEXT
}

@Composable
fun TitleBar(
    type: TitleBarType,
    title: String = "",
    // iconRes는 TEXT_AND_ICON 타입일 때만 의미가 있음
    iconRes: Int = 0,
    // onIconClick 역시 TEXT_AND_ICON 타입일 때만 의미가 있음
    onIconClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .heightIn(60.dp)
            .background(Color.Transparent)
            .padding(
                if(type == TitleBarType.BACK_AND_IMAGE_AND_TEXT) {
                    PaddingValues(
                        start = 13.dp, top = 13.dp, end = 13.dp, bottom = 13.dp
                    )
                } else {
                    PaddingValues(
                        start = 24.dp, top = 13.dp, end = 24.dp, bottom = 13.dp
                    )
                }
            )
            .windowInsetsPadding(WindowInsets.statusBars),
        verticalAlignment = Alignment.CenterVertically
    ) {
        when (type) {
            TitleBarType.TEXT_ONLY -> {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            TitleBarType.TEXT_AND_ICON -> {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(1f),
                )
                if (iconRes != 0 && onIconClick != null) {
                    Image(
                        painter = painterResource(id = iconRes),
                        contentDescription = "TitleBar Icon",
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .clickable { onIconClick() }
                    )
                }
            }

            // TitleBarType.BACK_AND_IMAGE_AND_TEXT 분기 내 코드 예시
            TitleBarType.BACK_AND_IMAGE_AND_TEXT -> {
                val navController = LocalNavController.current
                val coroutineScope = rememberCoroutineScope()
                var isClickEnabled by remember { mutableStateOf(true) }

                // 뒤로가기 버튼 (debounce 적용)
                Image(
                    painter = painterResource(id = R.drawable.back_button),
                    contentDescription = "back Icon",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .clickable(enabled = isClickEnabled) {
                            isClickEnabled = false
                            navController.popBackStack()
                            coroutineScope.launch {
                                delay(1000) // 500ms 동안 재클릭 방지
                                isClickEnabled = true
                            }
                        }
                )

                Spacer(modifier = Modifier.width(4.dp))

                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .size(24.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                )
            }
        }
    }
}
