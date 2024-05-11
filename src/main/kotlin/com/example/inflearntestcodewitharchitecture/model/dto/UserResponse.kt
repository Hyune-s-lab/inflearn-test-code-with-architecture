package com.example.inflearntestcodewitharchitecture.model.dto

import com.example.inflearntestcodewitharchitecture.model.UserStatus

class UserResponse(
    val id: Long,
    val email: String,
    val nickname: String,
    val status: UserStatus,
    val lastLoginAt: Long? = null,
)
