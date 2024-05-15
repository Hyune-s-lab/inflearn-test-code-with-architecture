package com.example.inflearntestcodewitharchitecture.user.service

import com.example.inflearntestcodewitharchitecture.common.exception.CertificationCodeNotMatchedException
import com.example.inflearntestcodewitharchitecture.common.exception.ResourceNotFoundException
import com.example.inflearntestcodewitharchitecture.user.domain.UserCreate
import com.example.inflearntestcodewitharchitecture.user.domain.UserStatus
import com.example.inflearntestcodewitharchitecture.user.domain.UserUpdate
import com.example.inflearntestcodewitharchitecture.user.infrastructure.UserEntity
import com.example.inflearntestcodewitharchitecture.user.infrastructure.UserRepository
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Clock
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository,
    private val mailSender: JavaMailSender,
) {
    fun getByEmail(email: String): UserEntity {
        return userRepository.findByEmailAndStatus(email, UserStatus.ACTIVE)
            ?: throw ResourceNotFoundException("Users", email)
    }

    fun getById(id: Long): UserEntity {
        return userRepository.findByIdAndStatus(id, UserStatus.ACTIVE)
            ?: throw ResourceNotFoundException("Users", id)
    }

    @Transactional
    fun create(userCreate: UserCreate): UserEntity {
        var userEntity = UserEntity(
            email = userCreate.email,
            nickname = userCreate.nickname,
            address = userCreate.address,
            status = UserStatus.PENDING,
            certificationCode = UUID.randomUUID().toString()
        )
        userEntity = userRepository.save(userEntity)
        val certificationUrl = generateCertificationUrl(userEntity)
        sendCertificationEmail(userCreate.email, certificationUrl)
        return userEntity
    }

    @Transactional
    fun update(id: Long, userUpdate: UserUpdate): UserEntity {
        val userEntity = getById(id).apply {
            nickname = userUpdate.nickname
            address = userUpdate.address
        }
        return userRepository.save(userEntity)
    }

    @Transactional
    fun login(id: Long) {
        val userEntity = userRepository.findById(id).orElseThrow { ResourceNotFoundException("Users", id) }
        userEntity.lastLoginAt = Clock.systemUTC().millis()
    }

    @Transactional
    fun verifyEmail(id: Long, certificationCode: String) {
        val userEntity = userRepository.findById(id).orElseThrow { ResourceNotFoundException("Users", id) }
        if (certificationCode != userEntity.certificationCode) {
            throw CertificationCodeNotMatchedException()
        }
        userEntity.status = UserStatus.ACTIVE
    }

    private fun sendCertificationEmail(email: String, certificationUrl: String) {
        val message = SimpleMailMessage().apply {
            setTo(email)
            subject = "Please certify your email address"
            text = "Please click the following link to certify your email address: $certificationUrl"
        }
        mailSender.send(message)
    }

    private fun generateCertificationUrl(userEntity: UserEntity): String {
        return "${"http://localhost:8080/api/users/${userEntity.id}"}/verify?certificationCode=${userEntity.certificationCode}"
    }
}
