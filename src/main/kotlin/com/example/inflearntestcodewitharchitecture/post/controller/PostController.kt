package com.example.inflearntestcodewitharchitecture.post.controller

import com.example.inflearntestcodewitharchitecture.post.controller.response.PostResponse
import com.example.inflearntestcodewitharchitecture.post.domain.PostUpdate
import com.example.inflearntestcodewitharchitecture.post.service.PostService
import com.example.inflearntestcodewitharchitecture.user.controller.UserController
import com.example.inflearntestcodewitharchitecture.user.infrastructure.PostEntity
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "게시물(posts)")
@RestController
@RequestMapping("/api/posts")
class PostController(
    private val postService: PostService,
    private val userController: UserController,
) {
    @GetMapping("/{id}")
    fun getPostById(@PathVariable id: Long): PostResponse {
        return toResponse(postService.getById(id))
    }

    @PutMapping("/{id}")
    fun updatePost(@PathVariable id: Long, @RequestBody postUpdate: PostUpdate): ResponseEntity<PostResponse> {
        return ResponseEntity
            .ok()
            .body(toResponse(postService.update(id, postUpdate)))
    }

    fun toResponse(postEntity: PostEntity): PostResponse {
        return PostResponse(
            id = postEntity.id!!,
            content = postEntity.content,
            createdAt = postEntity.createdAt,
            modifiedAt = postEntity.modifiedAt,
            writer = userController.toResponse(postEntity.writer)
        )
    }
}
