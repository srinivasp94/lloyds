package com.wiprodev.lloydstest.state


sealed class PostsResponse<T> {
    class Loading<T> : PostsResponse<T>()
    data class Success<T>(val data: T) : PostsResponse<T>()
    data class Failure<T>(val message: String) : PostsResponse<T>()
}