package com.example.ai_luna_compose.ui.screen.chat

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.ai_luna_compose.domain.entity.ChatItemType
import com.example.ai_luna_compose.domain.entity.ChatItemEntity
import com.example.ai_luna_compose.domain.repository.ChatDetailRepository
import com.example.ai_luna_compose.ui.common.GlobalDialogType
import com.example.ai_luna_compose.ui.common.GlobalDialogData
import com.example.ai_luna_compose.ui.screen.BaseViewModel
import com.example.ai_luna_compose.util.TimeUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatDetailViewModel @Inject constructor(private val repository: ChatDetailRepository) :
    BaseViewModel() {

    val chatList = mutableStateListOf<ChatItemEntity>()
    var messageText by mutableStateOf("")

    init {
        getChatList()
    }

    private fun getChatList() {
        viewModelScope.launch {
            val chatItemsResult = repository.getChatList(0)
            chatItemsResult.fold(onSuccess = {
                chatList.addAll(it)
            }, onFailure = {
                showGlobalErrorDialog(it.message?: "알 수 없는 오류")
            })
        }
    }

    fun onClickMsgSendBtn() {
        if (messageText.isEmpty()) return

        val chatItem = ChatItemEntity(
            chatItemType = ChatItemType.Me,
            text = messageText,
            time = TimeUtil.getCurrentTime()
        )

        chatList.add(chatItem)
        messageText = ""
    }
}
