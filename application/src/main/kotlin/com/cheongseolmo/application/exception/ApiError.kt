package com.cheongseolmo.application.exception

class ApiError(
    val message: String?,
    val code: String?,
    val data: Map<String, Any?>?,
)