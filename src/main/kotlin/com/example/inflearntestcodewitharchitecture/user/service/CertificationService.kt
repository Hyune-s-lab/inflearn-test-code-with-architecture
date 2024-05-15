package com.example.inflearntestcodewitharchitecture.user.service

import com.example.inflearntestcodewitharchitecture.user.service.port.MailSender
import org.springframework.stereotype.Service

@Service
class CertificationService(
    private val mailSender: MailSender,
) {
    fun send(email: String, userId: Long, certificationCode: String) {
        val certificationUrl = "http://localhost:8080/api/users/$userId/verify?certificationCode=$certificationCode"
        val title = "Please certify your email address"
        val content = "Please click the following link to certify your email address: $certificationUrl"

        mailSender.send(email, title, content)
    }
}
