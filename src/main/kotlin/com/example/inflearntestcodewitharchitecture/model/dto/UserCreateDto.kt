package com.example.inflearntestcodewitharchitecture.model.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class UserCreateDto(
    @param:JsonProperty("email") val email: String,
    @param:JsonProperty("nickname") val nickname: String,
    @param:JsonProperty("address") val address: String,
)
