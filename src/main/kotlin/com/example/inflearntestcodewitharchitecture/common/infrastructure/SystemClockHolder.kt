package com.example.inflearntestcodewitharchitecture.common.infrastructure

import com.example.inflearntestcodewitharchitecture.common.service.port.ClockHolder
import org.springframework.stereotype.Component
import java.time.Clock

@Component
class SystemClockHolder: ClockHolder {
    override fun millis(): Long {
        return Clock.systemUTC().millis()
    }
}
