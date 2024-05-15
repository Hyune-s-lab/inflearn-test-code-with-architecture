package com.example.inflearntestcodewitharchitecture.user.controller.response

import com.example.inflearntestcodewitharchitecture.user.domain.UserStatus

class UserResponse(
    val id: Long,
    val email: String,
    val nickname: String,
    val status: UserStatus,
    val lastLoginAt: Long? = null,
)
