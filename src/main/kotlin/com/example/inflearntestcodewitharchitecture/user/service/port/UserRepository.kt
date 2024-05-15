package com.example.inflearntestcodewitharchitecture.user.service.port

import com.example.inflearntestcodewitharchitecture.user.domain.UserStatus
import com.example.inflearntestcodewitharchitecture.user.infrastructure.UserEntity

interface UserRepository {
    fun findByEmailAndStatus(email: String, active: UserStatus): UserEntity?
    fun findByIdAndStatus(id: Long, active: UserStatus): UserEntity?
    fun findById(id: Long): UserEntity?

    fun save(userEntity: UserEntity): UserEntity
}
