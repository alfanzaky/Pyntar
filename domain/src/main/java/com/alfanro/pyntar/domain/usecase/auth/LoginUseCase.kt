package com.alfanro.pyntar.domain.usecase.auth

import com.alfanro.pyntar.domain.model.User
import com.alfanro.pyntar.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    sealed class ValidationError : Exception() {
        object EmailBlank : ValidationError()
        object PasswordBlank : ValidationError()
    }

    suspend operator fun invoke(email: String, password: String): Result<User> {
        if (email.isBlank()) return Result.failure(ValidationError.EmailBlank)
        if (password.isBlank()) return Result.failure(ValidationError.PasswordBlank)

        return authRepository.login(
            email = email.trim().lowercase(),
            password = password
        )
    }
}
