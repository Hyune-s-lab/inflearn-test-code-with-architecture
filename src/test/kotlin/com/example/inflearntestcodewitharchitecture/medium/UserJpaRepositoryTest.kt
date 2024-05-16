package com.example.inflearntestcodewitharchitecture.medium

import com.example.inflearntestcodewitharchitecture.user.domain.UserStatus
import com.example.inflearntestcodewitharchitecture.user.infrastructure.UserJpaRepository
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@DataJpaTest(showSql = true)
@TestPropertySource("classpath:test-application.properties")
@Sql("/sql/user-repository-test-data.sql")
class UserJpaRepositoryTest(
    @Autowired private val userJpaRepository: UserJpaRepository,
) {
    @Test
    fun `findByIdAndStatus 로 유저 데이터를 찾아올 수 있다`() {
        // given
        // when
        val result = userJpaRepository.findByIdAndStatus(1, UserStatus.ACTIVE)

        // then
        result shouldNotBe null
    }

    @Test
    fun `findByIdAndStatus 는 데이터가 없으면 Optional empty 를 내려준다`() {
        // given
        // when
        val result = userJpaRepository.findByIdAndStatus(1, UserStatus.PENDING)

        // then
        result shouldBe null
    }

    @Test
    fun `findByEmailAndStatus 로 유저 데이터를 찾아올 수 있다`() {
        // given
        // when
        val result = userJpaRepository.findByEmailAndStatus("kok202@naver.com", UserStatus.ACTIVE)

        // then
        result shouldNotBe null
    }

    @Test
    fun `findByEmailAndStatus 는 데이터가 없으면 Optional empty 를 내려준다`() {
        // given
        // when
        val result = userJpaRepository.findByEmailAndStatus("kok202@naver.com", UserStatus.PENDING)

        // then
        result shouldBe null
    }
}
