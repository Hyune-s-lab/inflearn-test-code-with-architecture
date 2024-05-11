package com.example.inflearntestcodewitharchitecture.service

import com.example.inflearntestcodewitharchitecture.exception.ResourceNotFoundException
import com.example.inflearntestcodewitharchitecture.model.dto.PostCreateDto
import com.example.inflearntestcodewitharchitecture.model.dto.PostUpdateDto
import com.example.inflearntestcodewitharchitecture.repository.PostEntity
import com.example.inflearntestcodewitharchitecture.repository.PostRepository
import org.springframework.stereotype.Service
import java.time.Clock

@Service
class PostService(
    private val postRepository: PostRepository,
    private val userService: UserService,
) {
    fun getPostById(id: Long): PostEntity {
        return postRepository.findById(id).orElseThrow { ResourceNotFoundException("Posts", id) }
    }

    fun createPost(postCreateDto: PostCreateDto): PostEntity {
        val postEntity = PostEntity(
            writer = userService.getByIdOrElseThrow(postCreateDto.writerId),
            content = postCreateDto.content,
            createdAt = Clock.systemUTC().millis()
        )
        return postRepository.save(postEntity)
    }

    fun updatePost(id: Long, postUpdateDto: PostUpdateDto): PostEntity {
        val postEntity = getPostById(id).apply {
            content = postUpdateDto.content
            modifiedAt = Clock.systemUTC().millis()
        }
        return postRepository.save(postEntity)
    }
}
