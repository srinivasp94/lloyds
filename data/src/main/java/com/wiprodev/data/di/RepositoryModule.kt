package com.wiprodev.data.di

import com.wiprodev.data.ApiService
import com.wiprodev.data.repository.PostRepositoryImpl
import com.wiprodev.domain.repository.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun providePostRepository(apiService: ApiService) : PostRepository{
        return PostRepositoryImpl(apiService)
    }
}