package com.example.inflearntestcodewitharchitecture.user.infrastructure

import com.example.inflearntestcodewitharchitecture.common.infrastructure.BaseEntity
import com.example.inflearntestcodewitharchitecture.user.domain.User
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

    override val id: Long? = null,
): BaseEntity() {

    constructor(user: User): this(
        id = user.id,
        email = user.email,
        nickname = user.nickname,
        address = user.address,
        certificationCode = user.certificationCode,
        status = user.status,
        lastLoginAt = user.lastLoginAt,
    )

    fun toModel(): User {
        return User(
            id = this.id,
            email = this.email,
            nickname = this.nickname,
            address = this.address,
            certificationCode = this.certificationCode,
            status = this.status,
            lastLoginAt = this.lastLoginAt,
        )
    }
}
