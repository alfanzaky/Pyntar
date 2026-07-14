package com.alfanro.pyntar.data.repository

import com.alfanro.pyntar.core.common.security.PasswordHasher
import com.alfanro.pyntar.core.database.dao.UserDao
import com.alfanro.pyntar.core.database.entity.UserEntity
import com.alfanro.pyntar.core.datastore.SessionDataStore
import com.alfanro.pyntar.data.mapper.toDomain
import com.alfanro.pyntar.domain.model.User
import com.alfanro.pyntar.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val sessionDataStore: SessionDataStore,
    private val passwordHasher: PasswordHasher
) : AuthRepository {

    override suspend fun register(
        username: String,
        email: String,
        password: String
    ): Result<User> {
        // Check for duplicate email
        if (userDao.getUserByEmail(email) != null) {
            return Result.failure(Exception("Email sudah terdaftar"))
        }
        // Check for duplicate username
        if (userDao.getUserByUsername(username) != null) {
            return Result.failure(Exception("Username sudah dipakai"))
        }

        val entity = UserEntity(
            id = UUID.randomUUID().toString(),
            username = username,
            email = email,
            passwordHash = passwordHasher.hash(password)
        )

        val rowId = userDao.insertUser(entity)
        return if (rowId != -1L) {
            sessionDataStore.saveUserId(entity.id)
            Result.success(entity.toDomain())
        } else {
            Result.failure(Exception("Gagal membuat akun. Coba lagi."))
        }
    }

    override suspend fun login(email: String, password: String): Result<User> {
        val entity = userDao.getUserByEmail(email)
            ?: return Result.failure(Exception("Email atau password salah"))

        val isValid = passwordHasher.verify(password, entity.passwordHash)
        return if (isValid) {
            sessionDataStore.saveUserId(entity.id)
            Result.success(entity.toDomain())
        } else {
            Result.failure(Exception("Email atau password salah"))
        }
    }

    override suspend fun logout() {
        sessionDataStore.clearUserId()
    }

    override fun getCurrentUser(): Flow<User?> {
        return sessionDataStore.userIdFlow.flatMapLatest { userId ->
            if (userId == null) {
                flowOf(null)
            } else {
                userDao.getUserById(userId).map { it?.toDomain() }
            }
        }
    }
}
