package com.example.appkotlin.features.recognize.presentation.viewmodel

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.appkotlin.features.recognize.domain.entities.InputImage
import com.example.appkotlin.features.recognize.domain.entities.RecognizeResult
import com.example.appkotlin.features.recognize.domain.services.RecognizeInteractor
import com.example.appkotlin.features.recognize.presentation.presenter.InputImagePresenter
import com.example.appkotlin.features.recognize.presentation.state.RecognizeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.tensorflow.lite.support.image.TensorImage
import javax.inject.Inject

@HiltViewModel
class RecognizeViewModel @Inject constructor(
    private val recognizeInteractor: RecognizeInteractor,
    private val inputImagePresenter: InputImagePresenter
) : ViewModel() {

    private val _uiState = MutableStateFlow(RecognizeUiState());
    val uiState: StateFlow<RecognizeUiState> = _uiState.asStateFlow();

    init {
        Log.d("viewmodel", "init")
        recognizeInteractor.open();
    }

    override fun onCleared() {
        Log.d("viewmodel", "close")
        recognizeInteractor.close();
        super.onCleared()
    }

    fun recognize(inputImage: InputImage): List<RecognizeResult>{
        return recognizeInteractor.recognize(inputImage);
    }

    private fun getInputImageFromBitmap(bitmap: Bitmap): InputImage{
        val width = recognizeInteractor.getWidth();
        val height = recognizeInteractor.getHeight();
        val newBitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
        val byteBuffer = TensorImage.fromBitmap(newBitmap).buffer;
        val inputImage = InputImage(byteBuffer, width, height);
        return inputImage;
    }

    fun pickImage(uri: Uri?){
        var bitmap: Bitmap? = null;
        var results: List<RecognizeResult>? = null;
        uri?.let {
            bitmap = inputImagePresenter.getBitmapFromUri(uri);
        }
        bitmap?.let {
            results = recognize(getInputImageFromBitmap(it));
        }
        _uiState.update {
            currentState ->
                currentState.copy(imageUri = uri, bitmap = bitmap, results = results)
        }
    }
}