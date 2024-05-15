package com.example.inflearntestcodewitharchitecture.user.controller.response

import com.example.inflearntestcodewitharchitecture.common.support.toResponse
import com.example.inflearntestcodewitharchitecture.user.domain.User
import com.example.inflearntestcodewitharchitecture.user.domain.UserStatus
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class UserResponseTest {
    @Test
    fun `User으로 응답을 생성할 수 있다`() {
        // given
        val user = User(
            id = 1,
            email = "kok202@naver.com",
            nickname = "kok202",
            address = "Seoul",
            status = UserStatus.ACTIVE,
            lastLoginAt = 100,
            certificationCode = "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab",
        )

        // when
        val userResponse = user.toResponse()

        // then
        userResponse.run {
            id shouldBe 1
            email shouldBe "kok202@naver.com"
            status shouldBe UserStatus.ACTIVE
            lastLoginAt shouldBe 100
        }
    }
}
