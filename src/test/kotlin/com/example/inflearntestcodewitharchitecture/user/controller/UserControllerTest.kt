package com.example.inflearntestcodewitharchitecture.user.controller

import com.example.inflearntestcodewitharchitecture.user.domain.UserStatus
import com.example.inflearntestcodewitharchitecture.user.domain.UserUpdate
import com.example.inflearntestcodewitharchitecture.user.infrastructure.UserJpaRepository
import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.matchers.shouldBe
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
    Sql(value = ["/sql/user-controller-test-data.sql"], executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
    Sql(value = ["/sql/delete-all-data.sql"], executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
)
class UserControllerTest(
    @Autowired private val mockMvc: MockMvc,
    @Autowired private val userJpaRepository: UserJpaRepository,
) {
    private val objectMapper = ObjectMapper()

    @Test
    fun `사용자는 특정 유저의 정보를 개인정보는 소거된채 전달 받을 수 있다`() {
        // given
        // when
        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.email").value("kok202@naver.com"))
            .andExpect(jsonPath("$.nickname").value("kok202"))
            .andExpect(jsonPath("$.address").doesNotExist())
            .andExpect(jsonPath("$.status").value("ACTIVE"))
    }

    @Test
    fun `사용자는 존재하지 않는 유저의 아이디로 api 호출할 경우 404 응답을 받는다`() {
        // given
        // when
        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/123456789"))
            .andExpect(status().isNotFound())
            .andExpect(content().string("Users에서 ID 123456789를 찾을 수 없습니다."))
    }

    @Test
    fun `사용자는 인증 코드로 계정을 활성화 시킬 수 있다`() {
        // given
        // when
        // then
        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/users/2/verify")
                .queryParam("certificationCode", "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab")
        )
            .andExpect(status().isFound())

        val userEntity = userJpaRepository.findById(1L).get()
        userEntity.status shouldBe UserStatus.ACTIVE
    }

    @Test
    fun `사용자는 인증 코드가 일치하지 않을 경우 권한 없음 에러를 내려준다`() {
        // given
        // when
        // then
        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/users/2/verify")
                .queryParam("certificationCode", "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaac")
        )
            .andExpect(status().isForbidden())
    }

    @Test
    fun `사용자는 내 정보를 불러올 때 개인정보인 주소도 갖고 올 수 있다`() {
        // given
        // when
        // then
        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/users/me")
                .header("EMAIL", "kok202@naver.com")
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.email").value("kok202@naver.com"))
            .andExpect(jsonPath("$.nickname").value("kok202"))
            .andExpect(jsonPath("$.address").value("Seoul"))
            .andExpect(jsonPath("$.status").value("ACTIVE"))
    }

    @Test
    fun `사용자는 내 정보를 수정할 수 있다`() {
        // given
        val userUpdate = UserUpdate(
            nickname = "kok202-n",
            address = "Pangyo"
        )

        // when
        // then
        mockMvc.perform(
            MockMvcRequestBuilders.put("/api/users/me")
                .header("EMAIL", "kok202@naver.com")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userUpdate))
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.email").value("kok202@naver.com"))
            .andExpect(jsonPath("$.nickname").value("kok202-n"))
            .andExpect(jsonPath("$.address").value("Pangyo"))
            .andExpect(jsonPath("$.status").value("ACTIVE"))
    }
}
