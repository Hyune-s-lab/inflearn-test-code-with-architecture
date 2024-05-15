package com.example.inflearntestcodewitharchitecture.common.support

import com.example.inflearntestcodewitharchitecture.post.controller.response.PostResponse
import com.example.inflearntestcodewitharchitecture.post.domain.Post
import com.example.inflearntestcodewitharchitecture.user.controller.response.MyProfileResponse
import com.example.inflearntestcodewitharchitecture.user.controller.response.UserResponse
import com.example.inflearntestcodewitharchitecture.user.domain.User

fun User.toResponse(): UserResponse {
    return UserResponse(
        id = id!!,
        email = email,
        nickname = nickname,
        status = status,
        lastLoginAt = lastLoginAt,
    )
}

fun User.toMyProfileResponse(): MyProfileResponse {
    return MyProfileResponse(
        id = id!!,
        email = email,
        nickname = nickname,
        status = status,
        address = address,
        lastLoginAt = lastLoginAt,
    )
}

fun Post.toResponse(): PostResponse {
    return PostResponse(
        id = id!!,
        content = content,
        createdAt = createdAt,
        modifiedAt = modifiedAt,
        writer = writer.toResponse()
    )
}
