package com.example.inflearntestcodewitharchitecture.repository

import com.example.inflearntestcodewitharchitecture.model.UserStatus
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserEntity, Long> {
    fun findByIdAndStatus(id: Long, userStatus: UserStatus): UserEntity?
    fun findByEmailAndStatus(email: String?, userStatus: UserStatus): UserEntity?
}
