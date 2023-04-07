package com.code.manager.useCase

import com.code.manager.controller.dto.CodeVerifyDto
import com.code.manager.database.repository.CodeRepository
import org.springframework.stereotype.Service

@Service
class VerifyCodeUseCase(
    private val codeRepository: CodeRepository
) {

    fun execute(codeVerifyDto: CodeVerifyDto): Boolean {
        val codeEntity = codeRepository.findByUserId(codeVerifyDto.userId)

        return codeEntity?.code == codeVerifyDto.code
    }
}