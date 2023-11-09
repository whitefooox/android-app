package com.example.appkotlin.features.recognize.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.appkotlin.features.recognize.presentation.context.AppContext
import com.example.appkotlin.features.recognize.presentation.ui.screen.GalleryScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        AppContext.set(applicationContext);

        super.onCreate(savedInstanceState)
        setContent {
            GalleryScreen(applicationContext)
        }
    }
}