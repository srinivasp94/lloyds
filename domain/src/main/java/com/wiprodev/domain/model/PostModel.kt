package com.wiprodev.domain.model

data class PostModel(
    val userId: Long,
    val id: Long,
    val title: String,
    val body: String,
    val likes: Long,
    val disLikes: Long,
    val views: Long
)
