package com.wiprodev.data

import com.wiprodev.data.model.Posts
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun fetchPosts(): Response<Posts>
}