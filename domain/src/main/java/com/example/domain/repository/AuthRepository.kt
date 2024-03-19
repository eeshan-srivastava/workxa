package com.example.domain.repository

interface AuthRepository {
    fun login()
    fun logout()
    fun isLoggedIn(): Boolean
}