package com.code.manager.controller

import com.code.manager.controller.dto.CodeVerifyDto
import com.code.manager.controller.dto.IdentityDto
import com.code.manager.useCase.GenerateCodeUseCase
import com.code.manager.useCase.VerifyCodeUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("codes")
class CodeManagerController(
    private val generateCodeUseCase: GenerateCodeUseCase,
    private val verifyCodeUseCase: VerifyCodeUseCase
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody identityDto: IdentityDto) {
        generateCodeUseCase.execute(identityDto)
    }

    @PostMapping("verify")
    fun verify(@RequestBody codeVerifyDto: CodeVerifyDto) : ResponseEntity<Unit> =
        if (verifyCodeUseCase.execute(codeVerifyDto)) ResponseEntity.ok().build()
        else ResponseEntity.badRequest().build()
}