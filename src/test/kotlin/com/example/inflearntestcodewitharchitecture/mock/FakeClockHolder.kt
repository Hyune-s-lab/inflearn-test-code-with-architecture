package com.example.inflearntestcodewitharchitecture.mock

import com.example.inflearntestcodewitharchitecture.common.service.port.ClockHolder

class FakeClockHolder(
    private val millis: Long = 0,
): ClockHolder {
    override fun millis(): Long {
        return millis
    }
}
