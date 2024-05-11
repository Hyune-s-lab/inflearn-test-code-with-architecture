package com.example.inflearntestcodewitharchitecture.model.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class PostUpdateDto(
    @param:JsonProperty("content") val content: String,
)
