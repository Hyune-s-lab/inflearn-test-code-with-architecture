package com.example.inflearntestcodewitharchitecture.mock

import com.example.inflearntestcodewitharchitecture.user.service.port.MailSender

class FakeMailSender: MailSender {
    lateinit var email: String
    lateinit var title: String
    lateinit var content: String

    override fun send(email: String, title: String, content: String) {
        this.email = email
        this.title = title
        this.content = content
    }
}
