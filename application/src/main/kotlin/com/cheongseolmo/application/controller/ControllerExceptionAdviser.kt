package com.cheongseolmo.application.controller

import com.cheongseolmo.application.exception.ApiError
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ControllerExceptionAdviser {
    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ApiError> {
        return ResponseEntity(
            ApiError(
                e.message,
                "unknown.error",
                null
            ), HttpStatus.BAD_REQUEST
        )
    }
}