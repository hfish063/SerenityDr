package com.example.backend.error

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Failed to locate entity")
class EntityNotFoundException : RuntimeException() {
}