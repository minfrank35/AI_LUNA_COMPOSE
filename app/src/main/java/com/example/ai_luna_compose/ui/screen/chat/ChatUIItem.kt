package com.example.ai_luna_compose.ui.screen.chat

enum class ChatItemType {
    Me,
    Other,
}

data class ChatUIItem(
    var chatItemType: ChatItemType,
    var text: String,
    var time: String,
    var imageRes: Int? = null
)
