package com.example.inflearntestcodewitharchitecture.post.controller.response

import com.example.inflearntestcodewitharchitecture.common.support.toResponse
import com.example.inflearntestcodewitharchitecture.post.domain.Post
import com.example.inflearntestcodewitharchitecture.user.domain.User
import com.example.inflearntestcodewitharchitecture.user.domain.UserStatus
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.time.Clock

class PostResponseTest {
    @Test
    fun `Post로 응답을 생성할 수 있다`() {
        // given
        val post = Post(
            id = 1,
            content = "helloworld",
            createdAt = Clock.systemUTC().millis(),
            writer = User(
                id = 1,
                email = "kok202@naver.com",
                nickname = "kok202",
                address = "Seoul",
                status = UserStatus.ACTIVE,
                certificationCode = "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab"
            )
        )

        // when
        val postResponse = post.toResponse()

        // then
        postResponse.run {
            content shouldBe "helloworld"
            writer.email shouldBe "kok202@naver.com"
            writer.nickname shouldBe "kok202"
            writer.status shouldBe UserStatus.ACTIVE
        }
    }
}
