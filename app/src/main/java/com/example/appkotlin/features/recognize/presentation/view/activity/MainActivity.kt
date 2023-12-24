package com.example.appkotlin.features.recognize.presentation.view.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.appkotlin.core.navigation.AppNavHost
import com.example.appkotlin.features.auth.presentation.widgets.MagicButton
import com.example.appkotlin.features.auth.presentation.widgets.MagicInputField
import com.example.appkotlin.features.recognize.presentation.view.compose.GalleryScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppNavHost()
        }
    }
}