package com.example.appkotlin.features.auth.presentation.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appkotlin.features.auth.domain.exceptions.AuthException
import com.example.appkotlin.features.auth.domain.interactors.AuthInteractor
import com.example.appkotlin.features.auth.presentation.state.AuthState
import com.example.appkotlin.features.auth.presentation.state.AuthStateType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authInteractor: AuthInteractor
) : ViewModel() {
    private val _authState = MutableStateFlow(AuthState());
    val authState = _authState.asStateFlow();

    private val exceptionHandler = CoroutineExceptionHandler { context, error ->
        _authState.update {
            it.copy(currentState = AuthStateType.ErrorAuthState, errorMessage = error.message.toString())
        }
    }

    fun signIn(email: String, password: String){
        _authState.update {
            it.copy(currentState = AuthStateType.LoadingAuthState)
        }
        viewModelScope.launch(exceptionHandler) {
            authInteractor.signIn(email, password);
            _authState.update {
                it.copy(currentState = AuthStateType.AuthenticatedState)
            }
        }
    }

    fun signUp(email: String, password: String){
        _authState.update {
            it.copy(currentState = AuthStateType.LoadingAuthState)
        }
        viewModelScope.launch(exceptionHandler) {
            authInteractor.signUp(email, password);
            _authState.update {
                it.copy(currentState = AuthStateType.AuthenticatedState)
            }
        }
    }
}