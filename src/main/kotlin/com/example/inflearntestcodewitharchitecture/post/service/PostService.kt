package com.example.inflearntestcodewitharchitecture.post.service

import com.example.inflearntestcodewitharchitecture.common.exception.ResourceNotFoundException
import com.example.inflearntestcodewitharchitecture.post.domain.Post
import com.example.inflearntestcodewitharchitecture.post.domain.PostCreate
import com.example.inflearntestcodewitharchitecture.post.domain.PostUpdate
import com.example.inflearntestcodewitharchitecture.post.service.port.PostRepository
import com.example.inflearntestcodewitharchitecture.user.service.UserService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Clock

@Service
class PostService(
    private val postRepository: PostRepository,
    private val userService: UserService,
) {
    fun getById(id: Long): Post {
        return postRepository.findById(id) ?: throw ResourceNotFoundException("Posts", id)
    }

    @Transactional
    fun create(postCreate: PostCreate): Post {
        val post = Post(
            postCreate = postCreate,
            createdAt = Clock.systemUTC().millis(),
            writer = userService.getById(postCreate.writerId)
        )
        return postRepository.save(post)
    }

    fun update(id: Long, postUpdate: PostUpdate): Post {
        val post = getById(id).update(postUpdate, Clock.systemUTC().millis())
        return postRepository.save(post)
    }
}
