package com.example.appkotlin.features.recognize.domain.services

import com.example.appkotlin.features.recognize.domain.dependencies.RecognizeRepository
import com.example.appkotlin.features.recognize.domain.entities.InputImage
import com.example.appkotlin.features.recognize.domain.entities.RecognizeResult

class RecognizeService(private val recognizeRepository: RecognizeRepository) {
    fun open(){
        recognizeRepository.open();
    }

    fun close(){
        recognizeRepository.close();
    }

    fun getWidth(): Int {
        return recognizeRepository.getWidth();
    }

    fun getHeight(): Int {
        return recognizeRepository.getHeight();
    }

    fun recognize(inputImage: InputImage): List<RecognizeResult> {
        return recognizeRepository.recognize(inputImage);
    }
}