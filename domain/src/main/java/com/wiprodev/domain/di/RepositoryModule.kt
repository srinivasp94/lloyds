package com.wiprodev.domain.di

import com.wiprodev.domain.usecase.FetchPostsUseCase
import com.wiprodev.domain.repository.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {


    @Provides
    @Singleton
    fun provideFetchPostUseCase(repository: PostRepository) : FetchPostsUseCase {
        return FetchPostsUseCase(repository)
    }
}