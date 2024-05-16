package com.example.inflearntestcodewitharchitecture.medium

import com.example.inflearntestcodewitharchitecture.post.domain.PostUpdate
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@SqlGroup(
    Sql(value = ["/sql/post-controller-test-data.sql"], executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
    Sql(value = ["/sql/delete-all-data.sql"], executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
)
class PostControllerTest(
    @Autowired private val mockMvc: MockMvc,
) {
    private val objectMapper = ObjectMapper()

    @Test
    fun `사용자는 게시물을 단건 조회 할 수 있다`() {
        // given
        // when
        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/posts/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").isNumber())
            .andExpect(jsonPath("$.content").value("helloworld"))
            .andExpect(jsonPath("$.writer.id").isNumber())
            .andExpect(jsonPath("$.writer.email").value("kok202@naver.com"))
            .andExpect(jsonPath("$.writer.nickname").value("kok202"))
    }

    @Test
    fun `사용자가 존재하지 않는 게시물을 조회할 경우 에러가 난다`() {
        // given
        // when
        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/posts/123456789"))
            .andExpect(status().isNotFound())
            .andExpect(content().string("Posts에서 ID 123456789를 찾을 수 없습니다."))
    }

    @Test
    fun `사용자는 게시물을 수정할 수 있다`() {
        // given
        val postUpdate = PostUpdate(
            content = "foobar"
        )

        // when
        // then
        mockMvc.perform(
            MockMvcRequestBuilders.put("/api/posts/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(postUpdate))
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").isNumber())
            .andExpect(jsonPath("$.content").value("foobar"))
            .andExpect(jsonPath("$.writer.id").isNumber())
            .andExpect(jsonPath("$.writer.email").value("kok202@naver.com"))
            .andExpect(jsonPath("$.writer.nickname").value("kok202"))
    }
}
