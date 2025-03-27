package com.example.ai_luna_compose.domain.repository

import com.example.ai_luna_compose.domain.entity.ChatItemEntity

interface ChatDetailRepository {
    suspend fun getChatList(index : Int): Result<List<ChatItemEntity>>
}