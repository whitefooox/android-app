package com.example.appkotlin.features.recognize.presentation.ui.screen

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.appkotlin.features.recognize.data.datasources.TFClassificator
import com.example.appkotlin.features.recognize.data.models.Recognize
import com.example.appkotlin.features.recognize.domain.entities.InputImage
import com.example.appkotlin.features.recognize.domain.entities.RecognizeResult
import com.example.appkotlin.features.recognize.presentation.presenter.InputImagePresenter
import org.tensorflow.lite.support.image.TensorImage

@Composable
fun GalleryScreen(context: Context) {

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    val tensorflow by remember { mutableStateOf(TFClassificator()) }
    var resutls by remember { mutableStateOf<List<RecognizeResult>?>(null) }

    fun recognize(bitmap: Bitmap): List<RecognizeResult>{
        val newBitmap = Bitmap.createScaledBitmap(bitmap, 224, 224, false);
        val byteBuffer = TensorImage.fromBitmap(newBitmap).buffer;
        val inputImage = InputImage(byteBuffer, 224, 224);
        val res = tensorflow.recognize(inputImage);
        return Recognize.fromMap(res);
    }

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent(), onResult = {
        imageUri = it
        imageUri?.let {
            bitmap = InputImagePresenter.getBitmapFromUri(imageUri!!);
        }
        bitmap?.let {
            resutls = recognize(bitmap!!)
        }
    })

    DisposableEffect(key1 = context, effect = {
        tensorflow.open();
        onDispose {
            tensorflow.close();
        }
    })
    
    resutls?.let { 
        Text(text = resutls!!.first().toString())
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        bitmap?.let { btm ->
            Image(
                bitmap = btm.asImageBitmap(),
                contentDescription = null,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .size(400.dp)
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