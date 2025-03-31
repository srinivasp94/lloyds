package com.wiprodev.data.model

data class Posts(
    val posts  : List<PostItem>,
    val total : Long,
    val skip : Long,
    val limit : Long

)
data class PostItem(
    val userId: Long,
    val id: Long,
    val title: String,
    val body: String,
    val tags: Array<String>,
    val reactions: Reactions,
    val views: Long

)

data class Reactions(
    val likes: Long,
    val dislikes: Long
)
