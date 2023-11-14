package com.example.appkotlin.features.recognize.presentation.state

import android.graphics.Bitmap
import android.net.Uri
import com.example.appkotlin.features.recognize.domain.entities.RecognizeResult

data class RecognizeUiState(
    val imageUri: Uri? = null,
    val bitmap: Bitmap? = null,
    val results: List<RecognizeResult>? = null
)
