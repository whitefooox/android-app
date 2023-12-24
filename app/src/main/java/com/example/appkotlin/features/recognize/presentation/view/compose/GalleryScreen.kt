package com.example.appkotlin.features.recognize.presentation.view.compose

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appkotlin.R
import com.example.appkotlin.features.auth.presentation.screens.AuthScreenEnd
import com.example.appkotlin.features.auth.presentation.widgets.MagicButton
import com.example.appkotlin.features.recognize.presentation.viewmodel.RecognizeViewModel

@Composable
fun GalleryScreen(viewModel: RecognizeViewModel = viewModel()) {

    val state by viewModel.uiState.collectAsState();

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent(), onResult = {
        viewModel.pickImage(it);
    })

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            //verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            state.results?.let {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp, bottom = 20.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .shadow(
                                elevation = 10.dp,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .background(color = Color.Gray),

                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(height = 10.dp))
                        Text(
                            text = "Распознанный объект:",
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp
                        )
                        Text(
                            text = "${it.first().label} (${it.first().confidence}%)",
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.height(height = 10.dp))
                }

            }
            state.bitmap?.let { btm ->
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    bitmap = btm.asImageBitmap(),
                    contentDescription = null,
                )
            }

            Button(onClick = {
                launcher.launch("image/*")
            }) {
                Text(text = "Распознать")
            }
        }
    }
}
