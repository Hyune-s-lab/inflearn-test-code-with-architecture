package com.example.inflearntestcodewitharchitecture.model.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class PostCreateDto(
    @param:JsonProperty("writerId") val writerId: Long,
    @param:JsonProperty("content") val content: String,
)
