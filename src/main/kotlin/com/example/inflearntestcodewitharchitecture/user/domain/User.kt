package com.example.inflearntestcodewitharchitecture.user.domain

import com.example.inflearntestcodewitharchitecture.common.exception.CertificationCodeNotMatchedException

data class User(
    val id: Long? = null,
    val email: String,
    val nickname: String,
    val address: String,
    val certificationCode: String,
    val status: UserStatus,
    val lastLoginAt: Long? = null,
) {
    constructor(userCreate: UserCreate, certificationCode: String): this(
        email = userCreate.email,
        nickname = userCreate.nickname,
        address = userCreate.address,
        certificationCode = certificationCode,
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

    fun login(lastLoginAt: Long): User {
        return User(
            id = this.id,
            email = this.email,
            nickname = this.nickname,
            address = this.address,
            certificationCode = this.certificationCode,
            status = this.status,
            lastLoginAt = lastLoginAt,
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
