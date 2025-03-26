package com.example.ai_luna_compose.ui.screen.chat

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ai_luna_compose.R
import com.example.ai_luna_compose.util.TimeUtil

class ChatDetailViewModel : ViewModel() {

    val chatList = mutableStateListOf<ChatUIItem>()
    var messageText by mutableStateOf("")

    init {
        getChatList()
    }

    private fun getChatList() {
        val chatItems = listOf(
            ChatUIItem(
                chatItemType = ChatItemType.Other,
                text = "안녕하세요, 무엇을 도와드릴까요? 아주 긴 메시지인 경우에도 자동 줄바꿈이 적용됩니다. 텍스트가 너무 길어지면 지정된 최대 폭을 넘지 않도록 합니다.",
                time = "09:30",
                imageRes = R.drawable.tarot_tint
            ),
            ChatUIItem(
                chatItemType = ChatItemType.Me,
                text = "안녕하세요! 타로 상담 받고 싶어요. 저도 메시지가 길어지면 자동 줄바꿈 되는지 확인해보세요.",
                time = "09:35",
                imageRes = R.drawable.tarot_tint
            ),
            ChatUIItem(
                chatItemType = ChatItemType.Other,
                text = "물론이죠, 어떤 점이 궁금하신가요?",
                time = "09:36",
                imageRes = R.drawable.tarot_tint
            ),
            ChatUIItem(
                chatItemType = ChatItemType.Me,
                text = "앞으로의 재물운이 궁금합니다.",
                time = "09:37",
                imageRes = R.drawable.tarot_tint
            ),
            ChatUIItem(
                chatItemType = ChatItemType.Me,
                text = "앞으로의 재물운이 궁금합니다.",
                time = "09:37",
                imageRes = R.drawable.tarot_tint
            )
        )

        chatList.addAll(chatItems)
    }

    fun onClickMsgSendBtn() {
        if(messageText.isEmpty()) return

        val chatItem = ChatUIItem(
            chatItemType = ChatItemType.Me,
            text = messageText,
            time = TimeUtil.getCurrentTime()
        )
        chatList.add(chatItem)
        messageText = ""
    }


}
