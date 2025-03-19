package com.example.ai_luna_compose.ui.common.etc

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.absoluteValue

// 간단한 보간 함수: start와 end 사이를 fraction 만큼 보간
private fun lerp(start: Float, stop: Float, fraction: Float): Float {
    return start + (stop - start) * fraction
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun <T> HorizontalViewPager(
    itemList: List<T>,
    modifier: Modifier = Modifier,
    height : Dp = 172.dp,
    itemSpacing: Dp = 16.dp,
    itemContent: @Composable (T) -> Unit
) {
    // PagerState 생성 시, 페이지 개수를 람다로 전달합니다.
    val pagerState = rememberPagerState { itemList.size }

    // contentPadding을 itemSpacing으로 설정하여 좌우 여백을 줍니다.
    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = itemSpacing),
        modifier = modifier.fillMaxWidth().wrapContentHeight()
    ) { page ->
        // 현재 페이지와의 오프셋 계산 (중앙에서 얼마나 떨어져 있는지)
        val pageOffset = ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction)
            .absoluteValue

        // 중앙은 scale 1.0, 옆은 scale 0.8 정도로 보간하여 적용
        val scale = lerp(0.8f, 1.0f, (1 - pageOffset).coerceIn(0f, 1f))
        val animatedScale by animateFloatAsState(targetValue = scale)

        Box(
            modifier = Modifier
                .padding(horizontal = itemSpacing / 2)
                .graphicsLayer { scaleY = animatedScale }
                // .fillMaxWidth().aspectRatio(1f) 대신:
                .fillMaxWidth()
                .height(height)
        ) {
            itemContent(itemList[page])
        }
    }
}
