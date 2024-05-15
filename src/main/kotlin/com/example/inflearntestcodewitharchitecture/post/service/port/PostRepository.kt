package com.example.inflearntestcodewitharchitecture.post.service.port

import com.example.inflearntestcodewitharchitecture.post.domain.Post

interface PostRepository {
    fun findById(id: Long): Post?
    fun save(post: Post): Post
}
