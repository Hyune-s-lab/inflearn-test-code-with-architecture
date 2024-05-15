package com.example.inflearntestcodewitharchitecture.user.domain

import com.example.inflearntestcodewitharchitecture.common.exception.CertificationCodeNotMatchedException
import com.example.inflearntestcodewitharchitecture.mock.FakeClockHolder
import com.example.inflearntestcodewitharchitecture.mock.FakeUuidHolder
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class UserTest {
    @Test
    fun `UserCreate 객체로 생성할 수 있다`() {
        // given
        val userCreate = UserCreate(
            email = "kok202@kakao.com",
            nickname = "kok202",
            address = "Pangyo"
        )

        // when
        val user = User(
            userCreate = userCreate,
            uuidHolder = FakeUuidHolder("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"),
        )

        // then
        user.run {
            id shouldBe null
            email shouldBe "kok202@kakao.com"
            nickname shouldBe "kok202"
            address shouldBe "Pangyo"
            status shouldBe UserStatus.PENDING
            certificationCode shouldBe "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"
        }
    }

    @Test
    fun `UserUpdate 객체로 데이터를 업데이트 할 수 있다`() {
        // given
        val user = User(
            id = 1L,
            email = "kok202@kakao.com",
            nickname = "kok202",
            address = "Seoul",
            status = UserStatus.ACTIVE,
            lastLoginAt = 100L,
            certificationCode = "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"
        )
        val userUpdate = UserUpdate(
            nickname = "kok202-k",
            address = "Pangyo"
        )
        // when
        val updateUser = user.update(userUpdate)

        // then
        updateUser.run {
            id shouldBe 1L
            email shouldBe "kok202@kakao.com"
            nickname shouldBe "kok202-k"
            address shouldBe "Pangyo"
            status shouldBe UserStatus.ACTIVE
            certificationCode shouldBe "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"
            lastLoginAt shouldBe 100L
        }
    }

    @Test
    fun `로그인을 할 수 있고 로그인시 마지막 로그인 시간이 변경된다`() {
        // given
        val user = User(
            id = 1L,
            email = "kok202@kakao.com",
            nickname = "kok202",
            address = "Seoul",
            status = UserStatus.ACTIVE,
            lastLoginAt = 100L,
            certificationCode = "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"
        )
        // when
        val loginUser = user.login(FakeClockHolder(1678530673958L))

        // then
        loginUser.lastLoginAt shouldBe 1678530673958L
    }

    @Test
    fun `유효한 인증 코드로 계정을 활성화 할 수 있다`() {
        // given
        val user = User(
            id = 1L,
            email = "kok202@kakao.com",
            nickname = "kok202",
            address = "Seoul",
            status = UserStatus.PENDING,
            lastLoginAt = 100L,
            certificationCode = "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"
        )

        // when
        val certificatedUser = user.certificate("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")

        // then
        certificatedUser.status shouldBe UserStatus.ACTIVE
    }

    @Test
    fun `잘못된 인증 코드로 계정을 활성화 하려하면 에러를 던진다`() {
        // given
        val user = User(
            id = 1L,
            email = "kok202@kakao.com",
            nickname = "kok202",
            address = "Seoul",
            status = UserStatus.PENDING,
            lastLoginAt = 100L,
            certificationCode = "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"
        )

        // when
        // then
        assertThrows<CertificationCodeNotMatchedException> {
            user.certificate("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab")
        }
    }
}
