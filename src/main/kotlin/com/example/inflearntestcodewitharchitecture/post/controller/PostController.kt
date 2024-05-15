package com.example.inflearntestcodewitharchitecture.post.controller

import com.example.inflearntestcodewitharchitecture.common.support.toResponse
import com.example.inflearntestcodewitharchitecture.post.controller.response.PostResponse
import com.example.inflearntestcodewitharchitecture.post.domain.PostUpdate
import com.example.inflearntestcodewitharchitecture.post.service.PostService
import io.swagger.v3.oas.annotations.tags.Tag
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
) {
    @GetMapping("/{id}")
    fun getPostById(@PathVariable id: Long): PostResponse {
        return postService.getById(id).toResponse()
    }

    @PutMapping("/{id}")
    fun updatePost(@PathVariable id: Long, @RequestBody postUpdate: PostUpdate): PostResponse {
        return postService.update(id, postUpdate).toResponse()
    }
}
