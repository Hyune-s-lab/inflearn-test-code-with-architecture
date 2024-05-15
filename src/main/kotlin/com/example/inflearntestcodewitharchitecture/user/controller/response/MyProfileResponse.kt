package com.example.inflearntestcodewitharchitecture.user.controller.response

import com.example.inflearntestcodewitharchitecture.user.domain.UserStatus

data class MyProfileResponse(
    val id: Long,
    val email: String,
    val nickname: String,
    val address: String,
    val status: UserStatus,
    val lastLoginAt: Long? = null,
)
