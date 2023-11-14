package com.example.appkotlin.features.recognize.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.appkotlin.features.recognize.domain.services.RecognizeUseCase
import com.example.appkotlin.features.recognize.presentation.ui.screen.GalleryScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var recognizeUseCase: RecognizeUseCase;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GalleryScreen(recognizeUseCase, applicationContext);
        }
    }
}