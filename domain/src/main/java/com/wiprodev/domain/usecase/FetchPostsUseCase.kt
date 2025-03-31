package com.wiprodev.domain.usecase

import com.wiprodev.domain.repository.PostRepository
import javax.inject.Inject

class FetchPostsUseCase @Inject constructor(private  val repository: PostRepository) {
    suspend fun invoke() = repository.getPosts()
}