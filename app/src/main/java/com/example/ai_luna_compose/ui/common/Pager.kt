package com.example.ai_luna_compose.ui.common

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import kotlin.math.absoluteValue

// 간단한 lerp 함수: start와 end 사이를 보간
private fun lerp(start: Float, stop: Float, fraction: Float): Float {
    return start + (stop - start) * fraction
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun <T> HorizontalViewPager(
    itemList: List<T>,
    modifier: Modifier = Modifier,
    itemContent: @Composable (T) -> Unit
) {
    // 페이지 개수를 lambda로 제공하여 PagerState 생성
    val pagerState = rememberPagerState { itemList.size }

    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 32.dp),
        modifier = modifier.fillMaxWidth()
    ) { page ->
        val pageOffset = ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction)
            .absoluteValue

        val scale = lerp(0.8f, 1.0f, (1 - pageOffset).coerceIn(0f, 1f))
        val animatedScale by animateFloatAsState(targetValue = scale)

        Box(
            modifier = Modifier
                .graphicsLayer { scaleY = animatedScale }
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
            itemContent(itemList[page])
        }
    }
}


