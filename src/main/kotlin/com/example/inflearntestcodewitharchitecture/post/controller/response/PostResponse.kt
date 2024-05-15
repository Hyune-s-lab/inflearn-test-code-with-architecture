package com.example.inflearntestcodewitharchitecture.post.controller.response

import com.example.inflearntestcodewitharchitecture.user.controller.response.UserResponse

data class PostResponse(
    val id: Long,
    val content: String,
    val createdAt: Long,
    val modifiedAt: Long? = null,
    val writer: UserResponse,
)
