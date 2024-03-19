package com.example.domain.usecase

import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class AuthUseCase @Inject constructor(private val authRepository: AuthRepository) {

    fun login() {
        authRepository.login()
    }

    fun logout() {
        authRepository.logout()
    }

    fun isLoggedIn(): Boolean{
        return authRepository.isLoggedIn()
    }

}
