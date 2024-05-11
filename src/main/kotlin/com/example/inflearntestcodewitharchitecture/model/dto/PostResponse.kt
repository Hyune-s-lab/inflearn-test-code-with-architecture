package com.example.inflearntestcodewitharchitecture.model.dto

data class PostResponse(
    val id: Long,
    val content: String,
    val createdAt: Long,
    val modifiedAt: Long? = null,
    val writer: UserResponse,
)
