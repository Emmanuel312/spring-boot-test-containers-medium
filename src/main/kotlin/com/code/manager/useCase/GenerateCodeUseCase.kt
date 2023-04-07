package com.code.manager.useCase

import com.code.manager.controller.dto.IdentityDto
import com.code.manager.database.entity.CodeEntity
import com.code.manager.database.repository.CodeRepository
import org.springframework.stereotype.Service

@Service
class GenerateCodeUseCase(
    private val codeGenerator: CodeGenerator,
    private val codeRepository: CodeRepository
) {

    fun execute(identityDto: IdentityDto) {
        val code = codeGenerator.generate()
        val codeEntity = CodeEntity(
            userId = identityDto.userId,
            email = identityDto.email,
            code = code
        )

        codeRepository.save(codeEntity)
        sendEmail(codeEntity.email, codeEntity.code)
    }

    private fun sendEmail(email: String, code: String) {
        println("Sending email: $email with code: $code")
    }
}