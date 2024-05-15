package com.example.inflearntestcodewitharchitecture.post.infrastructure

import com.example.inflearntestcodewitharchitecture.post.service.port.PostRepository
import com.example.inflearntestcodewitharchitecture.user.infrastructure.PostEntity
import org.springframework.stereotype.Repository

@Repository
class PostRepositoryImpl(
    private val postJpaRepository: PostJpaRepository,
): PostRepository {
    override fun findById(id: Long): PostEntity? {
        return postJpaRepository.findById(id).orElse(null)
    }

    override fun save(postEntity: PostEntity): PostEntity {
        return postJpaRepository.save(postEntity)
    }
}
