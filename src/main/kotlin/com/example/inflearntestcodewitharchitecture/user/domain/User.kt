package com.example.inflearntestcodewitharchitecture.user.domain

import com.example.inflearntestcodewitharchitecture.common.exception.CertificationCodeNotMatchedException
import com.example.inflearntestcodewitharchitecture.common.service.port.ClockHolder
import com.example.inflearntestcodewitharchitecture.common.service.port.UuidHolder

data class User(
    val id: Long? = null,
    val email: String,
    val nickname: String,
    val address: String,
    val certificationCode: String,
    val status: UserStatus,
    val lastLoginAt: Long? = null,
) {
    constructor(userCreate: UserCreate, uuidHolder: UuidHolder): this(
        email = userCreate.email,
        nickname = userCreate.nickname,
        address = userCreate.address,
        certificationCode = uuidHolder.random(),
        status = UserStatus.PENDING,
    )

    fun update(userUpdate: UserUpdate): User {
        return User(
            id = this.id,
            email = this.email,
            nickname = userUpdate.nickname,
            address = userUpdate.address,
            certificationCode = this.certificationCode,
            status = this.status,
            lastLoginAt = this.lastLoginAt,
        )
    }

    fun login(clockHolder: ClockHolder): User {
        return User(
            id = this.id,
            email = this.email,
            nickname = this.nickname,
            address = this.address,
            certificationCode = this.certificationCode,
            status = this.status,
            lastLoginAt = clockHolder.millis(),
        )
    }

    fun certificate(certificationCode: String): User {
        if (certificationCode != this.certificationCode) {
            throw CertificationCodeNotMatchedException()
        }

        return User(
            id = this.id,
            email = this.email,
            nickname = this.nickname,
            address = this.address,
            certificationCode = this.certificationCode,
            status = UserStatus.ACTIVE,
            lastLoginAt = this.lastLoginAt,
        )
    }
}
