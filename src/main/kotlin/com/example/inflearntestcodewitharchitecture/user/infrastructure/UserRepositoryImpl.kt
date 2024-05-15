package com.example.inflearntestcodewitharchitecture.user.infrastructure

import com.example.inflearntestcodewitharchitecture.user.domain.User
import com.example.inflearntestcodewitharchitecture.user.domain.UserStatus
import com.example.inflearntestcodewitharchitecture.user.service.port.UserRepository
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(
    private val userJpaRepository: UserJpaRepository,
): UserRepository {
    override fun findByEmailAndStatus(email: String, active: UserStatus): User? {
        return userJpaRepository.findByEmailAndStatus(email, active)?.toModel()
    }

    override fun findByIdAndStatus(id: Long, active: UserStatus): User? {
        return userJpaRepository.findByIdAndStatus(id, active)?.toModel()
    }

    override fun findById(id: Long): User? {
        return userJpaRepository.findById(id).orElse(null)?.toModel()
    }

    override fun save(user: User): User {
        return userJpaRepository.save(UserEntity(user)).toModel()
    }
}
