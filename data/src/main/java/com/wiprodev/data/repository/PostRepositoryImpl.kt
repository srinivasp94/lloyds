package com.wiprodev.data.repository

import android.util.Log
import com.wiprodev.data.ApiService
import com.wiprodev.data.mapper.toPosts
import com.wiprodev.domain.model.PostModel
import com.wiprodev.domain.repository.PostRepository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(private val apiService: ApiService) : PostRepository {
    override suspend fun getPosts(): List<PostModel> {
        val response = apiService.fetchPosts()
        return response.body()?.posts?.map {
            it.toPosts()
        } ?: emptyList()
    }
}