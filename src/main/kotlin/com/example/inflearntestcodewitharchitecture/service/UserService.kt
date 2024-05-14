package com.example.inflearntestcodewitharchitecture.service

import com.example.inflearntestcodewitharchitecture.exception.CertificationCodeNotMatchedException
import com.example.inflearntestcodewitharchitecture.exception.ResourceNotFoundException
import com.example.inflearntestcodewitharchitecture.model.UserStatus
import com.example.inflearntestcodewitharchitecture.model.dto.UserCreateDto
import com.example.inflearntestcodewitharchitecture.model.dto.UserUpdateDto
import com.example.inflearntestcodewitharchitecture.repository.UserEntity
import com.example.inflearntestcodewitharchitecture.repository.UserRepository
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Clock

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
    fun create(userCreateDto: UserCreateDto): UserEntity {
        var userEntity = UserEntity(
            email = userCreateDto.email,
            nickname = userCreateDto.nickname,
            address = userCreateDto.address,
            status = UserStatus.PENDING,
            certificationCode = java.util.UUID.randomUUID().toString()
        )
        userEntity = userRepository.save(userEntity)
        val certificationUrl = generateCertificationUrl(userEntity)
        sendCertificationEmail(userCreateDto.email, certificationUrl)
        return userEntity
    }

    @Transactional
    fun update(id: Long, userUpdateDto: UserUpdateDto): UserEntity {
        val userEntity = getById(id).apply {
            nickname = userUpdateDto.nickname
            address = userUpdateDto.address
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
