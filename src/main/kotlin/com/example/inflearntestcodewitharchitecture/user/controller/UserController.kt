package com.example.inflearntestcodewitharchitecture.user.controller

import com.example.inflearntestcodewitharchitecture.common.support.toMyProfileResponse
import com.example.inflearntestcodewitharchitecture.common.support.toResponse
import com.example.inflearntestcodewitharchitecture.user.controller.response.MyProfileResponse
import com.example.inflearntestcodewitharchitecture.user.controller.response.UserResponse
import com.example.inflearntestcodewitharchitecture.user.domain.UserUpdate
import com.example.inflearntestcodewitharchitecture.user.service.UserService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@Tag(name = "유저(users)")
@RestController
@RequestMapping("/api/users")
class UserController(
    private val userService: UserService,
) {

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): UserResponse {
        return userService.getById(id).toResponse()
    }

    @GetMapping("/{id}/verify")
    fun verifyEmail(
        @PathVariable id: Long,
        @RequestParam certificationCode: String,
    ): ResponseEntity<Void> {
        userService.verifyEmail(id, certificationCode)
        return ResponseEntity.status(HttpStatus.FOUND)
            .location(URI.create("http://localhost:3000"))
            .build()
    }

    @GetMapping("/me")
    fun getMyInfo(
        @RequestHeader("EMAIL") email: String, // 일반적으로 스프링 시큐리티를 사용한다면 UserPrincipal 에서 가져옵니다.
    ): MyProfileResponse {
        val user = userService.getByEmail(email)
        userService.login(user.id!!)
        return user.toMyProfileResponse()
    }

    @PutMapping("/me")
    fun updateMyInfo(
        @RequestHeader("EMAIL") email: String,  // 일반적으로 스프링 시큐리티를 사용한다면 UserPrincipal 에서 가져옵니다.
        @RequestBody userUpdate: UserUpdate,
    ): MyProfileResponse {
        var user = userService.getByEmail(email)
        user = userService.update(user.id!!, userUpdate)
        return user.toMyProfileResponse()
    }
}
