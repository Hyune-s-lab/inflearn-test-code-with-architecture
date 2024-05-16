package com.example.inflearntestcodewitharchitecture.post.service

import com.example.inflearntestcodewitharchitecture.mock.FakeClockHolder
import com.example.inflearntestcodewitharchitecture.mock.FakePostRepository
import com.example.inflearntestcodewitharchitecture.mock.FakeUserRepository
import com.example.inflearntestcodewitharchitecture.post.domain.Post
import com.example.inflearntestcodewitharchitecture.post.domain.PostCreate
import com.example.inflearntestcodewitharchitecture.post.domain.PostUpdate
import com.example.inflearntestcodewitharchitecture.user.domain.User
import com.example.inflearntestcodewitharchitecture.user.domain.UserStatus
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PostServiceTest {
    private val postRepository = FakePostRepository()
    private val userRepository = FakeUserRepository()
    private val postService = PostService(
        postRepository = postRepository,
        userRepository = userRepository,
        clockHolder = FakeClockHolder(1678530673958L),
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

        listOf(
            Post(
                id = 1,
                writer = userRepository.findById(1)!!,
                content = "helloworld",
                createdAt = 0L,
                modifiedAt = 0L
            )
        ).forEach { postRepository.save(it) }
    }

    @Test
    fun `byId는 존재하는 게시물을 내려준다`() {
        // given
        // when
        val result = postService.getById(1)

        // then
        result.content shouldBe "helloworld"
        result.writer.email shouldBe "kok202@naver.com"
    }

    @Test
    fun `postCreate 를 이용하여 게시물을 생성할 수 있다`() {
        // given
        val postCreate = PostCreate(
            writerId = 1,
            content = "foobar"
        )

        // when
        val result = postService.create(postCreate)

        // then
        result.id shouldNotBe null
        result.content shouldBe "foobar"
        result.createdAt shouldBe 1678530673958L
    }

    @Test
    fun `postUpdate 를 이용하여 게시물을 수정할 수 있다`() {
        // given
        val postUpdate = PostUpdate(
            content = "hello world :)"
        )

        // when
        postService.update(1, postUpdate)

        // then
        val postEntity = postService.getById(1)
        postEntity.content shouldBe "hello world :)"
        postEntity.modifiedAt shouldBe 1678530673958L
    }
}
