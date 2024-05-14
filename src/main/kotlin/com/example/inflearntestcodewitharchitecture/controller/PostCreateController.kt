package com.example.inflearntestcodewitharchitecture.controller

import com.example.inflearntestcodewitharchitecture.model.dto.PostCreateDto
import com.example.inflearntestcodewitharchitecture.model.dto.PostResponse
import com.example.inflearntestcodewitharchitecture.service.PostService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@Tag(name = "게시물(posts)")
@RestController
@RequestMapping("/api/posts")
class PostCreateController(
    private val postService: PostService,
    private val postController: PostController,
) {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createPost(@RequestBody postCreateDto: PostCreateDto): PostResponse {
        return postController.toResponse(postService.create(postCreateDto))
    }
}
