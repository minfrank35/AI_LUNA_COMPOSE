package com.example.ai_luna_compose.domain.entity

enum class ChatItemType {
    Me,
    Other,
}

data class ChatItemEntity(
    var chatItemType: ChatItemType,
    var text: String,
    var time: String,
    var imageRes: Int? = null
)
