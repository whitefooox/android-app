package com.example.appkotlin.features.auth.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appkotlin.R
import com.example.appkotlin.features.auth.presentation.state.AuthStateType
import com.example.appkotlin.features.auth.presentation.viewmodel.AuthViewModel
import com.example.appkotlin.features.auth.presentation.widgets.MagicButton
import com.example.appkotlin.features.auth.presentation.widgets.MagicError
import com.example.appkotlin.features.auth.presentation.widgets.MagicInputField

@Composable
fun AuthSignInScreen(
    onNavigate: () -> Unit,
    viewModel: AuthViewModel = viewModel()
) {
    val state by viewModel.authState.collectAsState();

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    if (state.currentState == AuthStateType.AuthenticatedState) onNavigate()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(painter = painterResource(id = R.drawable.auth_background))
            .padding(start = 50.dp, end = 50.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            //verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            MagicInputField(value = email, placeholderText = "email", onChange = {email = it})
            Spacer(modifier = Modifier.height(height = 20.dp))
            MagicInputField(value = password, placeholderText = "password", onChange = {password = it}, isSecret = true)
            Spacer(modifier = Modifier.height(height = 40.dp))
            if(state.currentState == AuthStateType.ErrorAuthState) MagicError(message = state.errorMessage.toString())
            MagicButton(text = "GO!", onClick = {
                viewModel.signIn(email, password);
            },
                isActive = state.currentState != AuthStateType.LoadingAuthState
            )
        }
    }
}