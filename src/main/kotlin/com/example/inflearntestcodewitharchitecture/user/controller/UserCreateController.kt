package com.example.inflearntestcodewitharchitecture.user.controller

import com.example.inflearntestcodewitharchitecture.user.controller.response.UserResponse
import com.example.inflearntestcodewitharchitecture.user.domain.UserCreate
import com.example.inflearntestcodewitharchitecture.user.infrastructure.UserEntity
import com.example.inflearntestcodewitharchitecture.user.service.UserService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@Tag(name = "유저(users)")
@RestController
@RequestMapping("/api/users")
class UserCreateController(
    private val userController: UserController,
    private val userService: UserService,
) {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createUser(@RequestBody userCreate: UserCreate): UserResponse {
        val userEntity: UserEntity = userService.create(userCreate)
        return userController.toResponse(userEntity)
    }
}
