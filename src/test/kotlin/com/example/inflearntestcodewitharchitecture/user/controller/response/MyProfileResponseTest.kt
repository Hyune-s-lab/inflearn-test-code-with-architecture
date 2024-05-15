package com.example.inflearntestcodewitharchitecture.user.controller.response

import com.example.inflearntestcodewitharchitecture.common.support.toMyProfileResponse
import com.example.inflearntestcodewitharchitecture.user.domain.User
import com.example.inflearntestcodewitharchitecture.user.domain.UserStatus
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class MyProfileResponseTest {
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
        val myProfileResponse = user.toMyProfileResponse()

        // then
        myProfileResponse.run {
            id shouldBe 1
            email shouldBe "kok202@naver.com"
            address shouldBe "Seoul"
            status shouldBe UserStatus.ACTIVE
            lastLoginAt shouldBe 100
        }
    }
}
