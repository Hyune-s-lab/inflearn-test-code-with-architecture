package com.example.inflearntestcodewitharchitecture.user.domain

import org.junit.jupiter.api.Test

class UserTest {
    @Test
    fun `UserCreate 객체로 생성할 수 있다`() {
        // given
        //        val userCreate: UserCreate = UserCreate.builder()
        //            .email("kok202@kakao.com")
        //            .nickname("kok202")
        //            .address("Pangyo")
        //            .build()
        //
        //        // when
        //        val user: User = User.from(userCreate, TestUuidHolder("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"))
        //
        //        // then
        //        AssertionsForClassTypes.assertThat(user.id).isNull()
        //        AssertionsForClassTypes.assertThat(user.email).isEqualTo("kok202@kakao.com")
        //        AssertionsForClassTypes.assertThat(user.nickname).isEqualTo("kok202")
        //        AssertionsForClassTypes.assertThat(user.address).isEqualTo("Pangyo")
        //        AssertionsForClassTypes.assertThat<UserStatus>(user.status).isEqualTo(UserStatus.PENDING)
        //        AssertionsForClassTypes.assertThat(user.certificationCode).isEqualTo("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
    }

    @Test
    fun `UserUpdate 객체로 데이터를 업데이트 할 수 있다`() {
        // given
        //        var user: User = User.builder()
        //            .id(1L)
        //            .email("kok202@kakao.com")
        //            .nickname("kok202")
        //            .address("Seoul")
        //            .status(UserStatus.ACTIVE)
        //            .lastLoginAt(100L)
        //            .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
        //            .build()
        //        val userUpdate: UserUpdate = UserUpdate.builder()
        //            .nickname("kok202-k")
        //            .address("Pangyo")
        //            .build()
        //
        //        // when
        //        user = user.update(userUpdate)
        //
        //        // then
        //        AssertionsForClassTypes.assertThat(user.id).isEqualTo(1L)
        //        AssertionsForClassTypes.assertThat(user.email).isEqualTo("kok202@kakao.com")
        //        AssertionsForClassTypes.assertThat(user.nickname).isEqualTo("kok202-k")
        //        AssertionsForClassTypes.assertThat(user.address).isEqualTo("Pangyo")
        //        AssertionsForClassTypes.assertThat<UserStatus>(user.status).isEqualTo(UserStatus.ACTIVE)
        //        AssertionsForClassTypes.assertThat(user.certificationCode).isEqualTo("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
        //        AssertionsForClassTypes.assertThat(user.lastLoginAt).isEqualTo(100L)
    }

    @Test
    fun `로그인을 할 수 있고 로그인시 마지막 로그인 시간이 변경된다`() {
        // given
        //        var user: User = User.builder()
        //            .id(1L)
        //            .email("kok202@kakao.com")
        //            .nickname("kok202")
        //            .address("Seoul")
        //            .status(UserStatus.ACTIVE)
        //            .lastLoginAt(100L)
        //            .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
        //            .build()
        //
        //        // when
        //        user = user.login(TestClockHolder(1678530673958L))
        //
        //        // then
        //        AssertionsForClassTypes.assertThat(user.lastLoginAt).isEqualTo(1678530673958L)
    }

    @Test
    fun `유효한 인증 코드로 계정을 활성화 할 수 있다`() {
        // given
        //        var user: User = User.builder()
        //            .id(1L)
        //            .email("kok202@kakao.com")
        //            .nickname("kok202")
        //            .address("Seoul")
        //            .status(UserStatus.PENDING)
        //            .lastLoginAt(100L)
        //            .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
        //            .build()
        //
        //        // when
        //        user = user.certificate("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
        //
        //        // then
        //        AssertionsForClassTypes.assertThat<UserStatus>(user.status).isEqualTo(UserStatus.ACTIVE)
    }

    @Test
    fun `잘못된 인증 코드로 계정을 활성화 하려하면 에러를 던진다`() {
        // given
        //        val user: User = User.builder()
        //            .id(1L)
        //            .email("kok202@kakao.com")
        //            .nickname("kok202")
        //            .address("Seoul")
        //            .status(UserStatus.PENDING)
        //            .lastLoginAt(100L)
        //            .certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
        //            .build()
        //
        //        // when
        //        // then
        //        AssertionsForClassTypes.assertThatThrownBy(ThrowableAssert.ThrowingCallable { user.certificate("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab") })
        //            .isInstanceOf(CertificationCodeNotMatchedException::class.java)
    }
}
