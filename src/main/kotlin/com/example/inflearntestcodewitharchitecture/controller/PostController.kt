package com.example.inflearntestcodewitharchitecture.controller

import com.example.inflearntestcodewitharchitecture.model.dto.PostResponse
import com.example.inflearntestcodewitharchitecture.model.dto.PostUpdateDto
import com.example.inflearntestcodewitharchitecture.repository.PostEntity
import com.example.inflearntestcodewitharchitecture.service.PostService
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
        return toResponse(postService.getPostById(id))
    }

    @PutMapping("/{id}")
    fun updatePost(@PathVariable id: Long, @RequestBody postUpdateDto: PostUpdateDto): ResponseEntity<PostResponse> {
        return ResponseEntity
            .ok()
            .body(toResponse(postService.updatePost(id, postUpdateDto)))
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
