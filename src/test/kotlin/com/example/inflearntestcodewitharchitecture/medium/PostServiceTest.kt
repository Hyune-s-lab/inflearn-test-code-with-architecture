package com.example.inflearntestcodewitharchitecture.medium

import com.example.inflearntestcodewitharchitecture.post.domain.PostCreate
import com.example.inflearntestcodewitharchitecture.post.domain.PostUpdate
import com.example.inflearntestcodewitharchitecture.post.service.PostService
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup

@SpringBootTest
@TestPropertySource("classpath:test-application.properties")
@SqlGroup(
    Sql(value = ["/sql/post-service-test-data.sql"], executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
    Sql(value = ["/sql/delete-all-data.sql"], executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
)
class PostServiceTest(
    @Autowired private val postService: PostService,
) {
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
        result.createdAt shouldBeGreaterThan 0
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
        postEntity.modifiedAt shouldNotBe null
        postEntity.modifiedAt!! shouldBeGreaterThan 0
    }
}
