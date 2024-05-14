package com.example.inflearntestcodewitharchitecture.controller

import com.example.inflearntestcodewitharchitecture.model.dto.MyProfileResponse
import com.example.inflearntestcodewitharchitecture.model.dto.UserResponse
import com.example.inflearntestcodewitharchitecture.model.dto.UserUpdateDto
import com.example.inflearntestcodewitharchitecture.repository.UserEntity
import com.example.inflearntestcodewitharchitecture.service.UserService
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
class UserController(private val userService: UserService) {

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): UserResponse {
        return toResponse(userService.getById(id))
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
        val userEntity: UserEntity = userService.getByEmail(email)
        userService.login(userEntity.id!!)
        return toMyProfileResponse(userEntity)
    }

    @PutMapping("/me")
    fun updateMyInfo(
        @RequestHeader("EMAIL") email: String,  // 일반적으로 스프링 시큐리티를 사용한다면 UserPrincipal 에서 가져옵니다.
        @RequestBody userUpdateDto: UserUpdateDto,
    ): MyProfileResponse {
        var userEntity: UserEntity = userService.getByEmail(email)
        userEntity = userService.update(userEntity.id!!, userUpdateDto)
        return toMyProfileResponse(userEntity)
    }

    fun toResponse(userEntity: UserEntity): UserResponse {
        return UserResponse(
            id = userEntity.id!!,
            email = userEntity.email,
            nickname = userEntity.nickname,
            status = userEntity.status,
            lastLoginAt = userEntity.lastLoginAt,
        )
    }

    fun toMyProfileResponse(userEntity: UserEntity): MyProfileResponse {
        return MyProfileResponse(
            id = userEntity.id!!,
            email = userEntity.email,
            nickname = userEntity.nickname,
            status = userEntity.status,
            address = userEntity.address,
            lastLoginAt = userEntity.lastLoginAt,
        )
    }
}
