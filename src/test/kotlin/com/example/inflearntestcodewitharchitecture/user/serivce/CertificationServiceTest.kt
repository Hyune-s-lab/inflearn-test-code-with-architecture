package com.example.inflearntestcodewitharchitecture.user.serivce

import com.example.inflearntestcodewitharchitecture.mock.FakeMailSender
import com.example.inflearntestcodewitharchitecture.user.service.CertificationService
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CertificationServiceTest {

    private val fakeMailSender: FakeMailSender = FakeMailSender()

    @Test
    fun `이메일과 컨텐츠가 제대로 만들어져서 보내지는지 테스트한다`() {
        // given
        val certificationService = CertificationService(fakeMailSender)

        // when
        certificationService.send("kok202@naver.com", 1, "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")

        // then
        fakeMailSender.email shouldBe "kok202@naver.com"
        fakeMailSender.title shouldBe "Please certify your email address"
        fakeMailSender.content shouldBe "Please click the following link to certify your email address: http://localhost:8080/api/users/1/verify?certificationCode=aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"
    }
}
