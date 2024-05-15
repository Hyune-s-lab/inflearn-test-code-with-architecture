package com.example.inflearntestcodewitharchitecture.post.controller

import com.example.inflearntestcodewitharchitecture.post.controller.response.PostResponse
import com.example.inflearntestcodewitharchitecture.post.domain.PostCreate
import com.example.inflearntestcodewitharchitecture.post.service.PostService
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
    fun createPost(@RequestBody postCreate: PostCreate): PostResponse {
        return postController.toResponse(postService.create(postCreate))
    }
}
