package com.example.inflearntestcodewitharchitecture.mock

import com.example.inflearntestcodewitharchitecture.common.service.port.UuidHolder

class FakeUuidHolder(
    private val uuid: String,
): UuidHolder {
    override fun random(): String {
        return uuid
    }
}
