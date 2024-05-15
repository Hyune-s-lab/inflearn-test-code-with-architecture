package com.example.inflearntestcodewitharchitecture.post.service.port

import com.example.inflearntestcodewitharchitecture.user.infrastructure.PostEntity

interface PostRepository {
    fun findById(id: Long): PostEntity?
    fun save(postEntity: PostEntity): PostEntity
}
