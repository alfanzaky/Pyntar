package com.alfanro.pyntar.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alfanro.pyntar.domain.model.User
import com.alfanro.pyntar.domain.usecase.auth.GetCurrentUserUseCase
import com.alfanro.pyntar.domain.usecase.auth.LoginUseCase
import com.alfanro.pyntar.domain.usecase.auth.LogoutUseCase
import com.alfanro.pyntar.domain.usecase.auth.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class AuthUiState {
    object Idle : AuthUiState()
    object Loading : AuthUiState()
    data class Success(val user: User) : AuthUiState()
    data class Error(val message: String) : AuthUiState()
}

sealed class SessionState {
    object Loading : SessionState()
    data class Active(val user: User) : SessionState()
    object None : SessionState()
}

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    val sessionState: StateFlow<SessionState> = getCurrentUserUseCase()
        .map { if (it == null) SessionState.None else SessionState.Active(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), SessionState.Loading)

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _uiState.update { AuthUiState.Loading }
            val result = loginUseCase(email, password)
            _uiState.update {
                result.fold(
                    onSuccess = { AuthUiState.Success(it) },
                    onFailure = {
                        val msg = when (it) {
                            is LoginUseCase.ValidationError.EmailBlank -> "Email tidak boleh kosong"
                            is LoginUseCase.ValidationError.PasswordBlank -> "Password tidak boleh kosong"
                            else -> it.message ?: "Terjadi kesalahan"
                        }
                        AuthUiState.Error(msg)
                    }
                )
            }
        }
    }

    fun register(
        username: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        viewModelScope.launch {
            _uiState.update { AuthUiState.Loading }
            val result = registerUseCase(
                RegisterUseCase.Params(username, email, password, confirmPassword)
            )
            _uiState.update {
                result.fold(
                    onSuccess = { AuthUiState.Success(it) },
                    onFailure = {
                        val msg = when (it) {
                            is RegisterUseCase.ValidationError.UsernameBlank -> "Username tidak boleh kosong"
                            is RegisterUseCase.ValidationError.UsernameTooShort -> "Username minimal 3 karakter"
                            is RegisterUseCase.ValidationError.EmailBlank -> "Email tidak boleh kosong"
                            is RegisterUseCase.ValidationError.EmailInvalid -> "Format email tidak valid"
                            is RegisterUseCase.ValidationError.PasswordBlank -> "Password tidak boleh kosong"
                            is RegisterUseCase.ValidationError.PasswordTooShort -> "Password minimal 8 karakter"
                            is RegisterUseCase.ValidationError.PasswordMismatch -> "Konfirmasi password tidak cocok"
                            else -> it.message ?: "Terjadi kesalahan"
                        }
                        AuthUiState.Error(msg)
                    }
                )
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase()
            _uiState.update { AuthUiState.Idle }
        }
    }

    fun resetState() {
        _uiState.update { AuthUiState.Idle }
    }
}
