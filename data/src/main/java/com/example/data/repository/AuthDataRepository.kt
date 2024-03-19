package com.example.data.repository

import com.example.data.local.pref.PreferenceProvider
import com.example.domain.repository.AuthRepository
import javax.inject.Inject

class AuthDataRepository @Inject constructor(
    private val preferenceProvider: PreferenceProvider,
) :  AuthRepository {

    override fun login() {
        preferenceProvider.updateIsLoggedIn(true)
    }

    override fun logout() {
        preferenceProvider.updateIsLoggedIn(false)
    }

    override fun isLoggedIn(): Boolean {
        return preferenceProvider.getIsLoggedIn()
    }

}
