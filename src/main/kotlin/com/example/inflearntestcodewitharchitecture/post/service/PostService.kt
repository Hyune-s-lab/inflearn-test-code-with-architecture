package com.example.inflearntestcodewitharchitecture.post.service

import com.example.inflearntestcodewitharchitecture.common.exception.ResourceNotFoundException
import com.example.inflearntestcodewitharchitecture.common.service.port.ClockHolder
import com.example.inflearntestcodewitharchitecture.post.domain.Post
import com.example.inflearntestcodewitharchitecture.post.domain.PostCreate
import com.example.inflearntestcodewitharchitecture.post.domain.PostUpdate
import com.example.inflearntestcodewitharchitecture.post.service.port.PostRepository
import com.example.inflearntestcodewitharchitecture.user.service.port.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostService(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository,

    private val clockHolder: ClockHolder,
) {
    fun getById(id: Long): Post {
        return postRepository.findById(id) ?: throw ResourceNotFoundException("Posts", id)
    }

    @Transactional
    fun create(postCreate: PostCreate): Post {
        val post = Post(
            postCreate = postCreate,
            clockHolder = clockHolder,
            writer = userRepository.findById(postCreate.writerId)
                ?: throw ResourceNotFoundException("User", postCreate.writerId)
        )
        return postRepository.save(post)
    }

    fun update(id: Long, postUpdate: PostUpdate): Post {
        val post = getById(id).update(postUpdate, clockHolder.millis())
        return postRepository.save(post)
    }
}
