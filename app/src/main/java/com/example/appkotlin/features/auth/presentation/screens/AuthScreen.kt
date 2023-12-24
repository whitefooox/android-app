package com.example.appkotlin.features.auth.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appkotlin.R
import com.example.appkotlin.features.auth.presentation.widgets.MagicButton

enum class AuthScreenEnd {
    SIGNIN,
    SIGNUP
}
@Composable
//@Preview
fun AuthScreen(onNavigate: (end: AuthScreenEnd) -> Unit) {
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
            Text(
                text = "Welcome\nto MagicScan!",
                fontSize = 40.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp),
                painter = painterResource(id = R.drawable.auth_rabbit),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(40.dp))
            MagicButton(text = "Sign in", onClick = { onNavigate(AuthScreenEnd.SIGNIN) })
            Spacer(modifier = Modifier.height(20.dp))
            MagicButton(text = "Sign up", onClick = { onNavigate(AuthScreenEnd.SIGNUP) })
        }
    }
}