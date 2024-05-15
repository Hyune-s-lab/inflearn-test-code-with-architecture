package com.example.inflearntestcodewitharchitecture.common.exception

class ResourceNotFoundException: RuntimeException {
    constructor(datasource: String, id: Long): super(datasource + "에서 ID " + id + "를 찾을 수 없습니다.")
    constructor(datasource: String, id: String): super(datasource + "에서 ID " + id + "를 찾을 수 없습니다.")
}
