package com.example.inflearntestcodewitharchitecture.user.service

import com.example.inflearntestcodewitharchitecture.common.exception.ResourceNotFoundException
import com.example.inflearntestcodewitharchitecture.common.service.port.ClockHolder
import com.example.inflearntestcodewitharchitecture.common.service.port.UuidHolder
import com.example.inflearntestcodewitharchitecture.user.domain.User
import com.example.inflearntestcodewitharchitecture.user.domain.UserCreate
import com.example.inflearntestcodewitharchitecture.user.domain.UserStatus
import com.example.inflearntestcodewitharchitecture.user.domain.UserUpdate
import com.example.inflearntestcodewitharchitecture.user.service.port.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val certificationService: CertificationService,

    private val uuidHolder: UuidHolder,
    private val clockHolder: ClockHolder
) {
    fun getByEmail(email: String): User {
        return userRepository.findByEmailAndStatus(email, UserStatus.ACTIVE)
            ?: throw ResourceNotFoundException("Users", email)
    }

    fun getById(id: Long): User {
        return userRepository.findByIdAndStatus(id, UserStatus.ACTIVE)
            ?: throw ResourceNotFoundException("Users", id)
    }

    @Transactional
    fun create(userCreate: UserCreate): User {
        val user = userRepository.save(User(userCreate, uuidHolder))
        certificationService.send(user.email, user.id!!, user.certificationCode)
        return user
    }

    @Transactional
    fun update(id: Long, userUpdate: UserUpdate): User {
        val user = getById(id).update(userUpdate)
        return userRepository.save(user)
    }

    @Transactional
    fun login(id: Long) {
        val user = userRepository.findById(id) ?: throw ResourceNotFoundException("Users", id)
        val loginUser = user.login(clockHolder)
        userRepository.save(loginUser)
    }

    @Transactional
    fun verifyEmail(id: Long, certificationCode: String) {
        val user = userRepository.findById(id) ?: throw ResourceNotFoundException("Users", id)
        val certificatedUser = user.certificate(certificationCode)
        userRepository.save(certificatedUser)
    }
}
