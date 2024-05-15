package com.example.inflearntestcodewitharchitecture.post.domain

import com.example.inflearntestcodewitharchitecture.common.service.port.ClockHolder
import com.example.inflearntestcodewitharchitecture.user.domain.User

data class Post(
    val id: Long? = null,
    val content: String,
    val createdAt: Long,
    val modifiedAt: Long? = null,
    val writer: User,
) {
    constructor(postCreate: PostCreate, clockHolder: ClockHolder, writer: User): this(
        id = null,
        content = postCreate.content,
        createdAt = clockHolder.millis(),
        writer = writer,
    )

    fun update(postUpdate: PostUpdate, modifiedAt: Long): Post {
        return Post(
            id = this.id,
            content = postUpdate.content,
            createdAt = this.createdAt,
            modifiedAt = modifiedAt,
            writer = this.writer,
        )
    }
}
