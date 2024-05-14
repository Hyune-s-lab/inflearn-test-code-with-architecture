package com.example.inflearntestcodewitharchitecture.controller

import com.example.inflearntestcodewitharchitecture.model.dto.UserCreateDto
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@SqlGroup(
    Sql(value = ["/sql/delete-all-data.sql"], executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
)
class UserCreateControllerTest(
    @Autowired private val mockMvc: MockMvc,
) {
    @MockBean
    private lateinit var mailSender: JavaMailSender

    private val objectMapper = ObjectMapper()

    @Test
    fun `사용자는 회원 가입을 할 수있고 회원가입된 사용자는 PENDING 상태이다`() {
        // given
        val userCreateDto = UserCreateDto(
            email = "kok202@kakao.com",
            nickname = "kok202",
            address = "Pangyo"
        )
        BDDMockito.doNothing().`when`(mailSender).send(
            ArgumentMatchers.any(SimpleMailMessage::class.java)
        )

        // when
        // then
        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userCreateDto))
        )
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").isNumber())
            .andExpect(jsonPath("$.email").value("kok202@kakao.com"))
            .andExpect(jsonPath("$.nickname").value("kok202"))
            .andExpect(jsonPath("$.status").value("PENDING"))
    }
}
