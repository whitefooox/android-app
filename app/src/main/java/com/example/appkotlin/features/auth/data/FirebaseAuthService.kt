package com.example.appkotlin.features.auth.data

import android.util.Log
import com.example.appkotlin.features.auth.domain.dependencies.IAuthService
import com.example.appkotlin.features.auth.domain.entities.User
import com.example.appkotlin.features.auth.domain.exceptions.AuthException
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.auth
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.tasks.await
import kotlin.jvm.Throws

class FirebaseAuthService : IAuthService {

    private var auth: FirebaseAuth = Firebase.auth

    @Throws(AuthException::class)
    override suspend fun signIn(user: User) {
        try {
            auth.signInWithEmailAndPassword(user.email, user.password).await()
        } catch (exception: Exception){
            throw AuthException(exception.message.toString())
        }
    }
    @Throws(AuthException::class)
    override suspend fun signUp(user: User) {
        try {
            auth.createUserWithEmailAndPassword(user.email, user.password).await()
        } catch (exception: Exception){
            throw AuthException(exception.message.toString())
        }
    }

    override fun signOut(user: User) {
        TODO("Not yet implemented")
    }
}