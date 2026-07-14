package com.alfanro.pyntar.domain.repository

import com.alfanro.pyntar.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun register(
        username: String,
        email: String,
        password: String
    ): Result<User>

    suspend fun login(
        email: String,
        password: String
    ): Result<User>

    suspend fun logout()

    fun getCurrentUser(): Flow<User?>
}
