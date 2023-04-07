package com.code.manager.database.repository

import com.code.manager.database.entity.CodeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CodeRepository : JpaRepository<CodeEntity, UUID> {
    fun findByUserId(userId: String) : CodeEntity?
}