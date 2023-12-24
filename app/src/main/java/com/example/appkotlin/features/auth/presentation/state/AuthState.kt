package com.example.appkotlin.features.auth.presentation.state

enum class AuthStateType {
    InitialAuthState,
    AuthenticatedState,
    LoadingAuthState,
    ErrorAuthState
}
data class AuthState(
    val currentState: AuthStateType = AuthStateType.InitialAuthState,
    val errorMessage: String? = null
)
