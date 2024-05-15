package com.example.inflearntestcodewitharchitecture.post.domain

import com.fasterxml.jackson.annotation.JsonProperty

data class PostUpdate(
    @param:JsonProperty("content") val content: String,
)
