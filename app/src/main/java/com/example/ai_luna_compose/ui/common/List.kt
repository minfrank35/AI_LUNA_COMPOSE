package com.example.ai_luna_compose.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview
@Composable
fun PreviewCommList() {
    // 예시 아이템 리스트
    val sampleList = listOf("Item 1", "Item 2", "Item 3", "Item 4")

    CommList(
        list = sampleList,
        itemContent = { index ->
            // 각 아이템을 Text로 표시 (padding 추가)
            Text(
                text = sampleList[index],
                modifier = Modifier.padding(16.dp)
            )
        }
    )
}

@Composable
fun <T> CommList(
    modifier: Modifier = Modifier,
    list: List<T>,
    itemContent: @Composable (Int) -> Unit
) {
    Card(
        shape = RoundedCornerShape(5.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF091522).copy(alpha = 0.7f)
        ),
        modifier = modifier
    ) {
        LazyColumn {
            itemsIndexed(list) { index, _ ->
                itemContent(index)
                if (index < list.lastIndex) {
                    Divider(modifier = Modifier.padding(horizontal = 12.dp))
                }
            }
        }
    }
}