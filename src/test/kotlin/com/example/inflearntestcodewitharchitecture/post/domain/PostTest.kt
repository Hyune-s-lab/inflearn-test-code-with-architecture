package com.example.inflearntestcodewitharchitecture.post.domain

import com.example.inflearntestcodewitharchitecture.mock.FakeClockHolder
import com.example.inflearntestcodewitharchitecture.user.domain.User
import com.example.inflearntestcodewitharchitecture.user.domain.UserStatus
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PostTest {
    @Test
    fun `PostCreate으로 게시물을 만들 수 있다`() {
        // given
        val postCreate = PostCreate(1, "helloworld")
        val writer = User(
            id = 1L,
            email = "kok202@naver.com",
            nickname = "kok202",
            address = "Seoul",
            status = UserStatus.ACTIVE,
            certificationCode = "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab"
        )

        // when
        val post = Post(postCreate, FakeClockHolder(1679530673958L), writer)

        // then
        post.run {
            content shouldBe "helloworld"
            createdAt shouldBe 1679530673958L
            writer.email shouldBe "kok202@naver.com"
            writer.nickname shouldBe "kok202"
            writer.address shouldBe "Seoul"
            writer.status shouldBe UserStatus.ACTIVE
            writer.certificationCode shouldBe "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab"
        }
    }

    @Test
    fun `PostUpdate로 게시물을 수정할 수 있다`() {
        // given
        val postUpdate = PostUpdate("foobar")
        val writer = User(
            id = 1L,
            email = "kok202@naver.com",
            nickname = "kok202",
            address = "Seoul",
            status = UserStatus.ACTIVE,
            certificationCode = "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab"
        )
        val post = Post(
            id = 1L,
            content = "helloworld",
            createdAt = 1678530673958L,
            modifiedAt = 0L,
            writer = writer
        )

        // when
        val updatePost = post.update(postUpdate, 1679530673958L)

        // then
        updatePost.run {
            content shouldBe "foobar"
            createdAt shouldBe 1678530673958L
        }
    }
}
