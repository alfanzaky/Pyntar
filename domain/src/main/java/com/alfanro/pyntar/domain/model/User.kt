package com.alfanro.pyntar.domain.model

data class User(
    val id: String,
    val username: String,
    val email: String,
    val avatarUrl: String? = null,
    val joinedAt: Long = System.currentTimeMillis()
)
