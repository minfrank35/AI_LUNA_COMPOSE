package com.example.ai_luna_compose.ui.screen.main

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ai_luna_compose.R
import com.example.ai_luna_compose.ui.common.TitleBar
import com.example.ai_luna_compose.ui.common.TitleBarType
import com.example.ai_luna_compose.ui.common.etc.HorizontalViewPager
import com.example.ai_luna_compose.ui.theme.Gray
import com.example.ai_luna_compose.ui.theme.TypographyKorean
import com.example.ai_luna_compose.ui.theme.Yellow
import kotlin.math.absoluteValue

// 보간 함수: start와 end 사이를 fraction만큼 보간
private fun lerp(start: Float, stop: Float, fraction: Float): Float {
    return start + (stop - start) * fraction
}

@Composable
fun TarotScreen() {
    // 탭 상태 관리 (초기값: "Love")
    var selectedTab by remember { mutableStateOf("인기 타로") }

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
    val tarotImages =
        listOf(R.drawable.banner_luna, R.drawable.banner_couple, R.drawable.banner_money)

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
    val tabs = listOf("인기 타로", "재물 사업운", "연애운")
    // 각 탭에 해당하는 아이콘 리소스 매핑
    val tabImages = mapOf(
        "인기 타로" to painterResource(id = R.drawable.star_linear),
        "재물 사업운" to painterResource(id = R.drawable.money_linear),
        "연애운" to painterResource(id = R.drawable.heart_linear)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 23.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        tabs.forEach { tab ->
            TarotTabItem(
                text = tab,
                image = tabImages[tab] ?: painterResource(id = R.drawable.tarot_tint),
                isSelected = tab == selectedTab,
                onClick = { onTabSelected(tab) }
            )
        }
    }
}

@Composable
fun TarotTabItem(
    text: String,
    image: Painter,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (!isSelected) MaterialTheme.colorScheme.background else Yellow
    Row(
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(100)
            )
            .clickable { onClick() }
            .padding(top = 9.dp, bottom = 9.dp, start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = image,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            style = TypographyKorean.titleLarge
        )
    }
}

// 질문 데이터를 담을 데이터 클래스
data class TarotQuestion(
    val question: String,
    val viewCount: String,
    val thumbnailResId: Int,
    val category: String  // 예: "인기 타로", "재물 사업운", "연애운"
)


@Composable
fun TarotTabList(selectedTab: String) {
    // 전체 질문 항목 목록 (총 9개)
    val questions = listOf(
        TarotQuestion("0월 00일 오늘의 운세", "조회수 1만+", R.drawable.rab_today, "인기 타로"),
        TarotQuestion("인기 (남, 녀) 되는법", "조회수 1만+", R.drawable.rab_popular, "인기 타로"),
        TarotQuestion("솔로탈출 시기", "조회수 1만+", R.drawable.rab_solo_escape, "인기 타로"),
        TarotQuestion("2025년 신년 재물운", "조회수 1만+", R.drawable.rab_money_2025, "재물 사업운"),
        TarotQuestion("사업 시작할까요 말까요?", "조회수 1만+", R.drawable.rab_business_start, "재물 사업운"),
        TarotQuestion("부자 될 수 있을까요?", "조회수 1만+", R.drawable.rab_rich, "재물 사업운"),
        TarotQuestion("2025년 상반기 연애운", "조회수 1만+", R.drawable.rab_couple_2025, "연애운"),
        TarotQuestion("결혼은 언제하는게 좋을까?", "조회수 1만+", R.drawable.rab_marry_when, "연애운"),
        TarotQuestion("그 사람, 나를 좋아할까?", "조회수 1만+", R.drawable.rab_like_me, "연애운")
    )
    // 선택된 탭에 해당하는 질문만 필터링
    val filteredQuestions = questions.filter { it.category == selectedTab }

    // Card로 감싸서 elevation 적용
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp, end = 24.dp, top = 21.dp, bottom = 21.dp),
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
    ) {
        LazyColumn(
            modifier = Modifier.padding(15.dp)
        ) {
            itemsIndexed(filteredQuestions) { index, question ->
                QuestionListItem(
                    thumbnailPainter = painterResource(id = question.thumbnailResId),
                    questionTitle = question.question,
                    viewCount = question.viewCount
                )
                if (index < filteredQuestions.lastIndex) {
                    Divider(
                        modifier = Modifier.padding(top = 12.dp, bottom = 12.dp),
                        color = Color.Gray,
                        thickness = 1.dp
                    )
                }
            }
        }
    }
}


@Composable
fun QuestionListItem(
    thumbnailPainter: Painter,
    questionTitle: String,
    viewCount: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
    ) {
        // 질문 대표 이미지 (썸네일)
        Image(
            painter = thumbnailPainter,
            contentDescription = "Question Thumbnail",
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            // 질문 제목
            Text(
                text = questionTitle,
                style = TypographyKorean.titleMedium, // 미리 정의된 TypographyKorean 스타일 사용
            )
            Spacer(modifier = Modifier.height(8.dp))
            // 조회수 텍스트
            Text(
                text = viewCount,
                style = TypographyKorean.titleSmall, // 미리 정의된 TypographyKorean 스타일 사용
            )
        }
    }
}

