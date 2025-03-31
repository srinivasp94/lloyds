package com.wiprodev.data.mapper

import com.wiprodev.data.model.PostItem
import com.wiprodev.domain.model.PostModel

fun PostItem.toPosts() =
    PostModel(
        id = this.id,
        userId = this.userId,
        title = this.title,
        body = this.body,
        likes = this.reactions.likes,
        disLikes = this.reactions.dislikes,
        views = this.views
    )
