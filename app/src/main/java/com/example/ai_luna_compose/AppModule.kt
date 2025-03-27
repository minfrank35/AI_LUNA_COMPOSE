package com.example.ai_luna_compose

import com.example.ai_luna_compose.data.repository.ChatDetailRepositoryImpl
import com.example.ai_luna_compose.domain.repository.ChatDetailRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideChatRepository(): ChatDetailRepository = ChatDetailRepositoryImpl()
}