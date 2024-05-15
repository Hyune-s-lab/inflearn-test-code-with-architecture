package com.example.inflearntestcodewitharchitecture.user.infrastructure

import com.example.inflearntestcodewitharchitecture.common.infrastructure.BaseEntity
import com.example.inflearntestcodewitharchitecture.user.domain.UserStatus
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table

@Entity
@Table(name = "users")
class UserEntity(
    @Column(name = "email")
    var email: String,

    @Column(name = "nickname")
    var nickname: String,

    @Column(name = "address")
    var address: String,

    @Column(name = "certification_code")
    var certificationCode: String,

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    var status: UserStatus,

    @Column(name = "last_login_at")
    var lastLoginAt: Long? = null,
): BaseEntity()
