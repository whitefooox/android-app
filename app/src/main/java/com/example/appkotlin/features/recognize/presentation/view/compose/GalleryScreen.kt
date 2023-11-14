package com.example.appkotlin.features.recognize.presentation.view.compose

import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appkotlin.features.recognize.presentation.viewmodel.RecognizeViewModel

@Composable
fun GalleryScreen(context: Context, viewModel: RecognizeViewModel = viewModel()) {

    val state by viewModel.uiState.collectAsState();

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent(), onResult = {
        viewModel.pickImage(it);
    })

    DisposableEffect(key1 = context, effect = {
        viewModel.openService();
        onDispose {
            viewModel.closeService();
        }
    })
    
    state.results?.let {
        Text(text = it.first().toString())
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        state.bitmap?.let { btm ->
            Image(
                bitmap = btm.asImageBitmap(),
                contentDescription = null,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .size(800.dp)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Button(onClick = {
                launcher.launch("image/*")
            }) {
                Text(text = "Распознать")
            }
        }
    }
}