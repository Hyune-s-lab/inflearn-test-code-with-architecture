package com.example.inflearntestcodewitharchitecture.user.service.port

import com.example.inflearntestcodewitharchitecture.user.domain.User
import com.example.inflearntestcodewitharchitecture.user.domain.UserStatus

interface UserRepository {
    fun findByEmailAndStatus(email: String, active: UserStatus): User?
    fun findByIdAndStatus(id: Long, active: UserStatus): User?
    fun findById(id: Long): User?

    fun save(user: User): User
}
