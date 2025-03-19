package com.example.ai_luna_compose.ui.common
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

enum class TitleBarType {
    TEXT_ONLY,
    TEXT_AND_ICON
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
    TopAppBar(
        modifier = Modifier.height(60.dp),
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        contentPadding = PaddingValues(
            start = 24.dp, top = 13.dp, end = 24.dp, bottom = 13.dp
        )
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
                    modifier = Modifier.weight(1f)
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
        }
    }
}
