package com.example.myapplication.presentation.login

import com.example.domain.usecase.AuthUseCase
import com.example.myapplication.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
) : BaseViewModel() {

    fun login() {
        authUseCase.login()
    }

    fun isLoggedIn(): Boolean {
        return authUseCase.isLoggedIn()
    }

}
