package com.example.inflearntestcodewitharchitecture.controller

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "헬스 체크")
@RestController
class HealthCheckController {
    @GetMapping("/health_check.html")
    fun healthCheck() {
    }
}
