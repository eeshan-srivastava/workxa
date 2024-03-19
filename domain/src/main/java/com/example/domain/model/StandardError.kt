package com.example.domain.model

data class StandardError(
    var responseCode: Int? = null,
    val developerMessage: String? = null,
    val userMessage: Int? = null,
    val serverMessage: String? = null,
    val serverCode: String? = null,
) : Throwable()

enum class ErrorType {
    CRITICAL, NON_CRITICAL, API
}
