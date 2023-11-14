package com.example.appkotlin.features.recognize.domain.dependencies

import com.example.appkotlin.features.recognize.domain.entities.InputImage
import com.example.appkotlin.features.recognize.domain.entities.RecognizeResult

interface IRecognizeRepository {
    fun open();
    fun close();
    fun getWidth(): Int;
    fun getHeight(): Int;
    fun recognize(inputImage: InputImage): List<RecognizeResult>;
}