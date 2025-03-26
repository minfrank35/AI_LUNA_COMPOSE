package com.example.ai_luna_compose.ui.screen.chat

import android.view.ViewTreeObserver
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ai_luna_compose.R
import com.example.ai_luna_compose.ui.common.TitleBar
import com.example.ai_luna_compose.ui.common.TitleBarType
import com.example.ai_luna_compose.ui.theme.ChatBlue
import com.example.ai_luna_compose.ui.theme.FONT_GOWUN_DODUM
import com.example.ai_luna_compose.ui.theme.Gray
import com.example.ai_luna_compose.ui.theme.TypographyKorean
import com.example.ai_luna_compose.util.Keyboard
import com.example.ai_luna_compose.util.KeyboardAnimationEffect
import com.example.ai_luna_compose.util.TimeUtil
import com.example.ai_luna_compose.util.keyboardAsState
import com.example.ai_luna_compose.util.setInsetsAnimationCallback
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatDetailScreenView() {
    // 예시 채팅 데이터: 채팅 메시지와 시간 정보를 포함한 람다 리스트
    val viewModel: ChatDetailViewModel = viewModel()
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
//    val isKeyboardOpen by keyboardAsState()

    LaunchedEffect(key1 = viewModel.chatList.size) {
        if (viewModel.chatList.isNotEmpty()) {
            listState.scrollToItem(viewModel.chatList.size - 1)
        }
    }
    val progressFraction = remember { mutableStateOf(0f) }
    // 스크롤 대상 오프셋 계산(예시: 최대 스크롤 오프셋을 키보드 진행률과 연동)
    // 실제 계산은 여러분의 레이아웃에 맞게 조정해야 합니다.

    // 애니메이션 진행률에 따라 스크롤을 업데이트 (여기서는 단순 예시로 100dp 만큼 스크롤한다고 가정)
    LaunchedEffect(progressFraction.value) {
        // progressFraction 값이 바뀔 때마다 스크롤을 조금씩 업데이트
        // 예: progressFraction이 0.5라면 50dp 만큼 스크롤하도록
        val targetOffset = progressFraction.value * 3000  // 여기서 100은 조정 가능한 값
        listState.scrollBy(targetOffset)
    }

    KeyboardAnimationEffect(
        onProgressFraction = { fraction ->
            progressFraction.value = fraction
        },
        onAnimationEnd = {
            // 애니메이션 종료 시 최종 스크롤 위치 조정
            coroutineScope.launch {
                if (viewModel.chatList.isNotEmpty()) {
                    listState.animateScrollToItem(viewModel.chatList.size - 1)
                }
            }
        }
    )


    Box {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // 상단 TitleBar (백버튼 포함)
            TitleBar(
                type = TitleBarType.BACK_AND_IMAGE_AND_TEXT,
                title = "Luna",
                iconRes = R.drawable.tarot_tint
            )
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 7.dp, start = 16.dp, end = 16.dp),
                state = listState,
            ) {
                itemsIndexed(viewModel.chatList) { index, currItem ->
                    ChatItem(
                        chatItemType = currItem.chatItemType,
                        text = currItem.text,
                        time = currItem.time
                    )

                    if (index < viewModel.chatList.lastIndex) { // 마지막 아이템은 밑에 공간 나오면 안되서
                        val nextItem = viewModel.chatList[index + 1]
                        if (nextItem.chatItemType == currItem.chatItemType) {
                            Spacer(modifier = Modifier.height(7.dp))
                        } else {
                            Spacer(modifier = Modifier.height(24.dp))
                        }
                    }
                }

            }
            // 하단 입력 영역: TextField와 전송 버튼
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .imePadding()
                    .padding(start = 16.dp, bottom = 17.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(41.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                ) {
                    BasicTextField(
                        value = viewModel.messageText,
                        onValueChange = { viewModel.messageText = it },
                        textStyle = TypographyKorean.bodyMedium.copy(color = Color.Black),
                        modifier = Modifier.fillMaxSize(),
                        decorationBox = { innerTextField ->
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(start = 15.dp, end = 15.dp),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                if (viewModel.messageText.isEmpty()) {
                                    Text(
                                        text = "메시지 입력",
                                        style = TypographyKorean.bodyMedium.copy(color = Color.Black)
                                    )
                                }
                                innerTextField()
                            }
                        }
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))
                Image(
                    modifier = Modifier
                        .background(
                            if (viewModel.messageText.isEmpty()) Color.Gray else Color.White,
                            shape = CircleShape
                        )
                        .clickable {
                            viewModel.onClickMsgSendBtn()
                        }
                        .padding(horizontal = 10.dp, vertical = 10.dp),
                    painter = painterResource(id = R.drawable.arrow_right),
                    contentDescription = null
                )
            }
        }
    }
}


@Composable
fun ChatItem(
    chatItemType: ChatItemType,
    text: String,
    time: String,
    imageRes: Painter = painterResource(id = R.drawable.tarot_tint)
) {
    // 화면 폭을 가져와 채팅 버블의 최대 폭을 지정 (예: 65%로 제한)
    val configuration = LocalConfiguration.current
    val maxBubbleWidth = configuration.screenWidthDp.dp * 0.55f

    if (chatItemType == ChatItemType.Me) {
        // 내가 보낸 채팅: 오른쪽 정렬, 메시지 왼쪽에 보낸 시간 표시
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Bottom
        ) {
            ChatItemTime(time = time)
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .background(ChatBlue, shape = RoundedCornerShape(10.dp))
                    .widthIn(max = maxBubbleWidth)
            ) {
                Text(
                    text = text,
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp),
                    style = TypographyKorean.bodyMedium,
                    color = Color.White
                )
            }
        }
    } else {
        // 상대편 채팅: 왼쪽 정렬, 왼쪽에 동그란 이미지, 메시지 오른쪽에 보낸 시간 표시
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Bottom
        ) {
            Image(
                painter = imageRes,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .background(Color.White, shape = RoundedCornerShape(10.dp))
                    .widthIn(max = maxBubbleWidth)
            ) {
                Text(
                    text = text,
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp),
                    style = TypographyKorean.bodyMedium,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            ChatItemTime(time = time)
        }
    }
}

/**
 * time: "00:00" ~ "23:59"
 * time: "00:00:00" ~ "23:59:59"
 */
@Composable
fun ChatItemTime(time: String) {
    // 시간 파싱 (예: "09:30" 또는 "09:30:00")
    val hour = time.substringBefore(":").toIntOrNull() ?: 0
    val minute = time.substringAfter(":").substringBefore(":").toIntOrNull() ?: 0

    // 12시간제로 변환 및 오전/오후 결정
    val period = if (hour < 12) "오전" else "오후"
    val displayHour = when {
        hour == 0 || hour == 12 -> 12
        hour > 12 -> hour - 12
        else -> hour
    }
    val formattedMinute = if (minute < 10) "0$minute" else "$minute"

    Box(Modifier.background(Color.Transparent)) {
        Text(
            text = "$period $displayHour:$formattedMinute",
            color = Gray,
            fontFamily = FONT_GOWUN_DODUM,
            fontSize = 9.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewChatItemMe() {
    ChatItem(
        chatItemType = ChatItemType.Me,
        text = "안녕하세요! 타로 상담 받고 싶어요. 메시지가 길어지면 자동으로 줄바꿈 처리됩니다.",
        time = "09:35"
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewChatItemOther() {
    ChatItem(
        chatItemType = ChatItemType.Other,
        text = "안녕하세요, 무엇을 도와드릴까요? 아주 긴 메시지인 경우에도 자동 줄바꿈이 적용됩니다.",
        time = "09:30"
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewChatItemTimeAM() {
    ChatItemTime(time = "09:30")
}

@Preview(showBackground = true)
@Composable
fun PreviewChatItemTimePM() {
    ChatItemTime(time = "15:45")
}
