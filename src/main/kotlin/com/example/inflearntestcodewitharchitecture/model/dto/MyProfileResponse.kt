package com.example.inflearntestcodewitharchitecture.model.dto

import com.example.inflearntestcodewitharchitecture.model.UserStatus

data class MyProfileResponse(
    val id: Long,
    val email: String,
    val nickname: String,
    val address: String,
    val status: UserStatus,
    val lastLoginAt: Long? = null,
)
