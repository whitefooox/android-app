package com.example.appkotlin.features.auth.presentation.widgets

import android.view.GestureDetector
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MagicButton(
    text: String,
    onClick: () -> Unit,
    //width: Double = 100.0,
    height: Double = 60.0,
    textSize: Double = 24.0,
    color: Color = Color.Blue,
    textColor: Color = Color.White,
    isActive: Boolean = true
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            //.width(width.dp)
            .height(height.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(if (isActive) color else color.copy(alpha = 0.5F))
            .border(
                1.dp,
                color,
                shape = RoundedCornerShape(30.dp),
            )
            .clickable(enabled = isActive) { onClick(); }
    ) {
        Text(text = text, color = textColor, fontSize = textSize.sp)
    }
}