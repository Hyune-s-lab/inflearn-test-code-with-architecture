package com.example.inflearntestcodewitharchitecture.user.serivce

import com.example.inflearntestcodewitharchitecture.common.exception.CertificationCodeNotMatchedException
import com.example.inflearntestcodewitharchitecture.common.exception.ResourceNotFoundException
import com.example.inflearntestcodewitharchitecture.mock.FakeClockHolder
import com.example.inflearntestcodewitharchitecture.mock.FakeMailSender
import com.example.inflearntestcodewitharchitecture.mock.FakeUserRepository
import com.example.inflearntestcodewitharchitecture.mock.FakeUuidHolder
import com.example.inflearntestcodewitharchitecture.user.domain.User
import com.example.inflearntestcodewitharchitecture.user.domain.UserCreate
import com.example.inflearntestcodewitharchitecture.user.domain.UserStatus
import com.example.inflearntestcodewitharchitecture.user.domain.UserUpdate
import com.example.inflearntestcodewitharchitecture.user.service.CertificationService
import com.example.inflearntestcodewitharchitecture.user.service.UserService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class UserServiceTest {
    private val userRepository = FakeUserRepository()
    private val userService: UserService = UserService(
        userRepository = userRepository,
        certificationService = CertificationService(FakeMailSender()),
        clockHolder = FakeClockHolder(1678530673958L),
        uuidHolder = FakeUuidHolder("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab"),
    )

    @BeforeEach
    fun beforeEach() {
        listOf(
            User(
                id = 1,
                email = "kok202@naver.com",
                nickname = "kok202",
                address = "Seoul",
                certificationCode = "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa",
                status = UserStatus.ACTIVE,
                lastLoginAt = 0L,
            ), User(
                id = 2,
                email = "kok303@naver.com",
                nickname = "kok303",
                address = "Seoul",
                certificationCode = "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab",
                status = UserStatus.PENDING,
                lastLoginAt = 0L,
            )
        ).forEach { userRepository.save(it) }
    }

    @Test
    fun `byEmail은 ACTIVE 상태인 유저를 찾아올 수 있다`() {
        // given
        val email = "kok202@naver.com"

        // when
        val result = userService.getByEmail(email)

        // then
        result.nickname shouldBe "kok202"
    }

    @Test
    fun `byEmail은 PENDING 상태인 유저는 찾아올 수 없다`() {
        // given
        val email = "kok303@naver.com"

        // when
        // then
        shouldThrow<ResourceNotFoundException> {
            userService.getByEmail(email)
        }
    }

    @Test
    fun `byId는 ACTIVE 상태인 유저를 찾아올 수 있다`() {
        // given
        // when
        val result = userService.getById(1)

        // then
        result.nickname shouldBe "kok202"
    }

    @Test
    fun `byId는 PENDING 상태인 유저는 찾아올 수 없다`() {
        // given
        // when
        // then
        assertThrows<ResourceNotFoundException> {
            userService.getById(2)
        }
    }

    @Test
    fun `userCreate 를 이용하여 유저를 생성할 수 있다`() {
        // given
        val userCreate = UserCreate(
            email = "kok202@kakao.com",
            address = "Gyeongi",
            nickname = "kok202-k",
        )

        // when
        val result = userService.create(userCreate)

        // then
        result.id shouldNotBe null
        result.status shouldBe UserStatus.PENDING
        result.certificationCode shouldBe "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab"
    }

    @Test
    fun `userUpdate 를 이용하여 유저를 수정할 수 있다`() {
        // given
        val userUpdate = UserUpdate(
            address = "Incheon",
            nickname = "kok202-n",
        )

        // when
        userService.update(1, userUpdate)

        // then
        val userEntity = userService.getById(1)
        userEntity.id shouldNotBe null
        userEntity.address shouldBe "Incheon"
        userEntity.nickname shouldBe "kok202-n"
    }

    @Test
    fun `user를 로그인 시키면 마지막 로그인 시간이 변경된다`() {
        // given
        // when
        userService.login(1)

        // then
        val userEntity = userService.getById(1)
        userEntity.lastLoginAt shouldBe 1678530673958L
    }

    @Test
    fun `PENDING 상태의 사용자는 인증 코드로 ACTIVE 시킬 수 있다`() {
        // given
        // when
        userService.verifyEmail(2, "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab")

        // then
        val userEntity = userService.getById(2)
        userEntity.status shouldBe UserStatus.ACTIVE
    }

    @Test
    fun `PENDING 상태의 사용자는 잘못된 인증 코드를 받으면 에러를 던진다`() {
        // given
        // when
        // then
        assertThrows<CertificationCodeNotMatchedException> {
            userService.verifyEmail(2, "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaac")
        }
    }
}
