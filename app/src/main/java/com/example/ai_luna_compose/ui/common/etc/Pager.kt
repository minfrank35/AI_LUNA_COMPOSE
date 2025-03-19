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
    // 사용자가 조절할 수 있는 아이템 간의 간격 파라미터
    itemSpacing: Dp = 16.dp,
    itemContent: @Composable (T) -> Unit
) {
    // PagerState 생성 시, 페이지 개수를 람다로 전달합니다.
    val pagerState = rememberPagerState { itemList.size }

    // contentPadding을 itemSpacing으로 설정하여, 좌우 여백을 줍니다.
    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = itemSpacing),
        modifier = modifier.fillMaxWidth()
    ) { page ->
        // 현재 페이지와의 오프셋 계산 (중앙에서 얼마나 떨어져 있는지)
        val pageOffset = ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction)
            .absoluteValue

        // 중앙은 scale 1.0, 옆은 scale 0.8 정도로 보간하여 적용
        val scale = lerp(0.8f, 1.0f, (1 - pageOffset).coerceIn(0f, 1f))
        val animatedScale by animateFloatAsState(targetValue = scale)

        Box(
            modifier = Modifier
                // 각 아이템에 좌우 itemSpacing/2 만큼 패딩을 추가하여, 아이템 간의 간격을 조절
                .padding(horizontal = itemSpacing / 2)
                .graphicsLayer { scaleY = animatedScale }
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
            itemContent(itemList[page])
        }
    }
}


@Composable
fun SampleHorizontalViewPager() {
    // 예시 아이템 목록
    val items = listOf("One", "Two", "Three", "Four")

    // HorizontalViewPager 호출, itemSpacing을 24.dp로 조절
    HorizontalViewPager(
        itemList = items,
        itemSpacing = 24.dp
    ) { item ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Text(text = item, color = Color.Black)
        }
    }
}



