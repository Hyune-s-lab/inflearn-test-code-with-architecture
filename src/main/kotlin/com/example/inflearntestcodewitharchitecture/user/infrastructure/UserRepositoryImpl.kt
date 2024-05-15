package com.example.inflearntestcodewitharchitecture.user.infrastructure

import com.example.inflearntestcodewitharchitecture.user.domain.UserStatus
import com.example.inflearntestcodewitharchitecture.user.service.port.UserRepository
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(
    private val userJpaRepository: UserJpaRepository,
): UserRepository {
    override fun findByEmailAndStatus(email: String, active: UserStatus): UserEntity? {
        return userJpaRepository.findByEmailAndStatus(email, active)
    }

    override fun findByIdAndStatus(id: Long, active: UserStatus): UserEntity? {
        return userJpaRepository.findByIdAndStatus(id, active)
    }

    override fun findById(id: Long): UserEntity? {
        return userJpaRepository.findById(id).orElse(null)
    }

    override fun save(userEntity: UserEntity): UserEntity {
        return userJpaRepository.save(userEntity)
    }
}
