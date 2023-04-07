package com.code.manager.controller.dto

data class CodeVerifyDto(
    val userId: String,
    val email: String,
    val code: String,
)
