package com.example.inflearntestcodewitharchitecture.post.controller

import com.example.inflearntestcodewitharchitecture.common.exception.CertificationCodeNotMatchedException
import com.example.inflearntestcodewitharchitecture.common.exception.ResourceNotFoundException
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class ExceptionControllerAdvice {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException::class)
    fun resourceNotFoundException(exception: ResourceNotFoundException): String? {
        return exception.message
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(CertificationCodeNotMatchedException::class)
    fun certificationCodeNotMatchedException(exception: CertificationCodeNotMatchedException): String? {
        return exception.message
    }
}
