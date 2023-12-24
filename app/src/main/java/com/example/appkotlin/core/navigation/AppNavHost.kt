package com.example.appkotlin.core.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appkotlin.features.auth.presentation.screens.AuthScreen
import com.example.appkotlin.features.auth.presentation.screens.AuthScreenEnd
import com.example.appkotlin.features.auth.presentation.screens.AuthSignInScreen
import com.example.appkotlin.features.auth.presentation.screens.AuthSignUpScreen
import com.example.appkotlin.features.auth.presentation.viewmodel.AuthViewModel
import com.example.appkotlin.features.recognize.presentation.view.compose.GalleryScreen
import com.example.appkotlin.features.recognize.presentation.viewmodel.RecognizeViewModel
import dagger.hilt.android.AndroidEntryPoint

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "auth",
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable("main"){
            val recognizeViewModel = hiltViewModel<RecognizeViewModel>()
            GalleryScreen(viewModel = recognizeViewModel)
        }
        composable("signin"){
            val authViewModel = hiltViewModel<AuthViewModel>()
            AuthSignInScreen(onNavigate = {
                navController.navigate("main")
            }, viewModel = authViewModel)
        }
        composable("signup"){
            val authViewModel = hiltViewModel<AuthViewModel>()
            AuthSignUpScreen(onNavigate = {
                navController.navigate("main")
            }, viewModel = authViewModel)
        }
        composable("auth"){
            AuthScreen(onNavigate = {
                when (it){
                    AuthScreenEnd.SIGNIN -> navController.navigate("signin")
                    AuthScreenEnd.SIGNUP -> navController.navigate("signup")
                }
            })
        }
    }
}