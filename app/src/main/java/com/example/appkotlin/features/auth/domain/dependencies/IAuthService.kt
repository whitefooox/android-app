package com.example.appkotlin.features.auth.domain.dependencies

import com.example.appkotlin.features.auth.domain.entities.User

interface IAuthService {
    suspend fun signIn(user: User)
    suspend fun signUp(user: User)
    fun signOut(user: User)
}