package com.example.inflearntestcodewitharchitecture.post.service

import com.example.inflearntestcodewitharchitecture.common.exception.ResourceNotFoundException
import com.example.inflearntestcodewitharchitecture.post.domain.PostCreate
import com.example.inflearntestcodewitharchitecture.post.domain.PostUpdate
import com.example.inflearntestcodewitharchitecture.user.infrastructure.PostEntity
import com.example.inflearntestcodewitharchitecture.user.infrastructure.PostRepository
import com.example.inflearntestcodewitharchitecture.user.service.UserService
import org.springframework.stereotype.Service
import java.time.Clock

@Service
class PostService(
    private val postRepository: PostRepository,
    private val userService: UserService,
) {
    fun getById(id: Long): PostEntity {
        return postRepository.findById(id).orElseThrow { ResourceNotFoundException("Posts", id) }
    }

    fun create(postCreate: PostCreate): PostEntity {
        val postEntity = PostEntity(
            writer = userService.getById(postCreate.writerId),
            content = postCreate.content,
            createdAt = Clock.systemUTC().millis()
        )
        return postRepository.save(postEntity)
    }

    fun update(id: Long, postUpdate: PostUpdate): PostEntity {
        val postEntity = getById(id).apply {
            content = postUpdate.content
            modifiedAt = Clock.systemUTC().millis()
        }
        return postRepository.save(postEntity)
    }
}
