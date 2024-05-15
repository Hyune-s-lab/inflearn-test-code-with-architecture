package com.example.inflearntestcodewitharchitecture.user.infrastructure

import com.example.inflearntestcodewitharchitecture.user.domain.UserStatus
import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaRepository: JpaRepository<UserEntity, Long> {
    fun findByIdAndStatus(id: Long, userStatus: UserStatus): UserEntity?
    fun findByEmailAndStatus(email: String?, userStatus: UserStatus): UserEntity?
}
