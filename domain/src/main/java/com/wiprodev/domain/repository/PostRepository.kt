package com.wiprodev.domain.repository

import com.wiprodev.domain.model.PostModel


interface PostRepository {
    suspend fun getPosts() : List<PostModel>
}