package com.code.manager

import com.code.manager.database.repository.CodeRepository
import com.code.manager.useCase.CodeGenerator
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*

class CodeManagerIntegrationTest(
    @Autowired
    private val mockMvc: MockMvc,
    @Autowired
    private val codeRepository: CodeRepository,
    @Autowired
    private val codeGenerator: CodeGenerator,
) : IntegrationTest() {

    @Test
    fun `should generate and verify a correct code`() {
        val userId = UUID.randomUUID()
        val email = "johndoe@example.com"
        val identityDtoAsJson = "{\"userId\":\"$userId\",\"email\":\"$email\"}"

        mockMvc.perform(
            MockMvcRequestBuilders.post("/codes")
                .content(identityDtoAsJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isCreated)

        val codeEntity = codeRepository.findByUserId(userId.toString())
        val codeVerifyDtoAsJson = "{\"userId\":\"$userId\",\"email\":\"$email\",\"code\":\"${codeEntity?.code}\"}"

        mockMvc.perform(
            MockMvcRequestBuilders.post("/codes/verify")
                .content(codeVerifyDtoAsJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
    }

    @Test
    fun `should generate and verify an incorrect code`() {
        val userId = UUID.randomUUID()
        val code = codeGenerator.generate()
        val email = "johndoe@example.com"
        val identityDtoAsJson = "{\"userId\":\"$userId\",\"email\":\"$email\"}"
        val codeVerifyDtoAsJson = "{\"userId\":\"$userId\",\"email\":\"$email\",\"code\":\"$code\"}"

        mockMvc.perform(
            MockMvcRequestBuilders.post("/codes")
                .content(identityDtoAsJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isCreated)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/codes/verify")
                .content(codeVerifyDtoAsJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().is4xxClientError)
    }
}