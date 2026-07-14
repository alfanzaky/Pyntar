package com.alfanro.pyntar.domain.usecase.auth

import com.alfanro.pyntar.domain.model.User
import com.alfanro.pyntar.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    data class Params(
        val username: String,
        val email: String,
        val password: String,
        val confirmPassword: String
    )

    sealed class ValidationError : Exception() {
        object UsernameBlank : ValidationError()
        object UsernameTooShort : ValidationError()
        object EmailBlank : ValidationError()
        object EmailInvalid : ValidationError()
        object PasswordBlank : ValidationError()
        object PasswordTooShort : ValidationError()
        object PasswordMismatch : ValidationError()
    }

    suspend operator fun invoke(params: Params): Result<User> {
        // Validate
        if (params.username.isBlank()) return Result.failure(ValidationError.UsernameBlank)
        if (params.username.length < 3) return Result.failure(ValidationError.UsernameTooShort)
        if (params.email.isBlank()) return Result.failure(ValidationError.EmailBlank)
        if (!"^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}\$".toRegex().matches(params.email)) {
            return Result.failure(ValidationError.EmailInvalid)
        }
        if (params.password.isBlank()) return Result.failure(ValidationError.PasswordBlank)
        if (params.password.length < 8) return Result.failure(ValidationError.PasswordTooShort)
        if (params.password != params.confirmPassword) {
            return Result.failure(ValidationError.PasswordMismatch)
        }

        return authRepository.register(
            username = params.username.trim(),
            email = params.email.trim().lowercase(),
            password = params.password
        )
    }
}
