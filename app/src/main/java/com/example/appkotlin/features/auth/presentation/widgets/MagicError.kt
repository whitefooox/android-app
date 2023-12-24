package com.example.appkotlin.features.auth.presentation.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MagicError(
    //width: Double,
    height: Double = 60.0,
    textSize: Double = 24.0,
    message: String
) {
    Text(
        modifier = Modifier
            .height(height = height.dp)
            .fillMaxWidth(),
        text = message,
        fontSize = textSize.sp,
        color = Color.Red,
        textAlign = TextAlign.Center
    )
}