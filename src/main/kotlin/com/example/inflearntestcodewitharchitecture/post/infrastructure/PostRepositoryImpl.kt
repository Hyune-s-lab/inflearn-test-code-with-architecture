package com.example.inflearntestcodewitharchitecture.post.infrastructure

import com.example.inflearntestcodewitharchitecture.post.domain.Post
import com.example.inflearntestcodewitharchitecture.post.service.port.PostRepository
import org.springframework.stereotype.Repository

@Repository
class PostRepositoryImpl(
    private val postJpaRepository: PostJpaRepository,
): PostRepository {
    override fun findById(id: Long): Post? {
        return postJpaRepository.findById(id).orElse(null)?.toModel()
    }

    override fun save(post: Post): Post {
        val postEntity = PostEntity(post)
        return postJpaRepository.save(postEntity).toModel()
    }
}
