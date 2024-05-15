package com.example.inflearntestcodewitharchitecture.user.service.port

interface MailSender {
    fun send(email: String, title: String, content: String)
}
