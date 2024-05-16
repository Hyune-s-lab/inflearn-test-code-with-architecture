package com.example.inflearntestcodewitharchitecture.medium

import com.example.inflearntestcodewitharchitecture.common.exception.CertificationCodeNotMatchedException
import com.example.inflearntestcodewitharchitecture.common.exception.ResourceNotFoundException
import com.example.inflearntestcodewitharchitecture.user.domain.UserCreate
import com.example.inflearntestcodewitharchitecture.user.domain.UserStatus
import com.example.inflearntestcodewitharchitecture.user.domain.UserUpdate
import com.example.inflearntestcodewitharchitecture.user.service.UserService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup

@SpringBootTest
@TestPropertySource("classpath:test-application.properties")
@SqlGroup(
    Sql(value = ["/sql/user-service-test-data.sql"], executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
    Sql(value = ["/sql/delete-all-data.sql"], executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
)
class UserServiceTest(
    @Autowired private val userService: UserService,
) {
    @MockBean
    private lateinit var mailSender: JavaMailSender

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

        BDDMockito.doNothing().`when`(mailSender).send(
            ArgumentMatchers.any(SimpleMailMessage::class.java)
        )

        // when
        val result = userService.create(userCreate)

        // then
        result.id shouldNotBe null
        result.status shouldBe UserStatus.PENDING
        // assertThat(result.getCertificationCode()).isEqualTo("T.T"); // FIXME
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
        userEntity.lastLoginAt!! shouldBeGreaterThan 0L
        // assertThat(result.getLastLoginAt()).isEqualTo("T.T"); // FIXME
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
