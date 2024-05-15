package com.example.inflearntestcodewitharchitecture.post.domain

import com.fasterxml.jackson.annotation.JsonProperty

data class PostCreate(
    @param:JsonProperty("writerId") val writerId: Long,
    @param:JsonProperty("content") val content: String,
)
