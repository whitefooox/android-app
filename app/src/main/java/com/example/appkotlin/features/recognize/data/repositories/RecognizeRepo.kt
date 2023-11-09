package com.example.appkotlin.features.recognize.data.repositories

import com.example.appkotlin.features.recognize.data.datasources.IRecognizeService
import com.example.appkotlin.features.recognize.data.models.Recognize
import com.example.appkotlin.features.recognize.domain.dependencies.RecognizeRepository
import com.example.appkotlin.features.recognize.domain.entities.InputImage
import com.example.appkotlin.features.recognize.domain.entities.RecognizeResult

class RecognizeRepo(private val recognizeService: IRecognizeService) : RecognizeRepository {
    override fun open() {
        recognizeService.open();
    }

    override fun close() {
        recognizeService.close();
    }

    override fun getWidth(): Int {
        return recognizeService.getWidth();
    }

    override fun getHeight(): Int {
        return recognizeService.getHeight();
    }

    override fun recognize(inputImage: InputImage): List<RecognizeResult> {
        return Recognize.fromMap(recognizeService.recognize(inputImage));
    }
}