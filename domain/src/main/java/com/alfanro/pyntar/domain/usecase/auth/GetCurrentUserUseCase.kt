package com.alfanro.pyntar.domain.usecase.auth

import com.alfanro.pyntar.domain.model.User
import com.alfanro.pyntar.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(): Flow<User?> = authRepository.getCurrentUser()
}
