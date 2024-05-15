package com.example.inflearntestcodewitharchitecture.user.domain

import com.fasterxml.jackson.annotation.JsonProperty

data class UserUpdate(
    @param:JsonProperty("nickname") val nickname: String,
    @param:JsonProperty("address") val address: String,
)
