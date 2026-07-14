package com.alfanro.pyntar.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

import androidx.room.Index

@Entity(
    tableName = "users",
    indices = [
        Index(value = ["email"], unique = true),
        Index(value = ["username"], unique = true)
    ]
)
data class UserEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val username: String,
    val email: String,
    val passwordHash: String,
    val avatarUrl: String? = null,
    val joinedAt: Long = System.currentTimeMillis()
)
