package com.example.appkotlin.features.auth.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MagicInputField(
    value: String,
    //width: Double,
    height: Double = 60.0,
    placeholderText: String,
    onChange: (text: String) -> Unit,
    textSize: Double = 22.0,
    isSecret: Boolean = false
) {
    TextField(
        modifier = Modifier
            .clip(RoundedCornerShape(30.dp))
            .fillMaxWidth()
            .height(height = height.dp)
            .border(
                2.dp,
                Color.Black,
                shape = RoundedCornerShape(30.dp),
            ),
        value = value,
        onValueChange = onChange,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color.White
        ),
        placeholder = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = placeholderText,
                fontSize = textSize.sp,
                textAlign = TextAlign.Center
            )
        },
        textStyle = TextStyle(
            fontSize = textSize.sp,
            textAlign = TextAlign.Center
        ),
        singleLine = true,
        visualTransformation = if (!isSecret) VisualTransformation.None else PasswordVisualTransformation()
    )
}