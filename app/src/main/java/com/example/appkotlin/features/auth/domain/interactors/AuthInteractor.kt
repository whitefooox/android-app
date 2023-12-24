package com.example.appkotlin.features.auth.domain.interactors

import com.example.appkotlin.features.auth.domain.dependencies.IAuthService
import com.example.appkotlin.features.auth.domain.entities.User
import com.example.appkotlin.features.auth.domain.exceptions.AuthException
import kotlinx.coroutines.runBlocking
import kotlin.jvm.Throws

class AuthInteractor(
    private val authService: IAuthService
) {
    @Throws(AuthException::class)
    suspend fun signIn(email: String, password: String){
        try {
            authService.signIn(User(email = email, password = password));
        } catch (exception: AuthException){
            throw exception
        }
    }

    @Throws(AuthException::class)
    suspend fun signUp(email: String, password: String){
        try {
            authService.signUp(User(email = email, password = password));
        }catch (exception: AuthException){
            throw exception
        }

    }
}