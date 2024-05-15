package com.example.inflearntestcodewitharchitecture.common.infrastructure

import com.example.inflearntestcodewitharchitecture.common.service.port.UuidHolder
import org.springframework.stereotype.Component
import java.util.*

@Component
class SystemUuidHolder: UuidHolder {
    override fun random(): String {
        return UUID.randomUUID().toString()
    }
}
