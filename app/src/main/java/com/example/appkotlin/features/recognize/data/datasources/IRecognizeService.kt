package com.example.appkotlin.features.recognize.data.datasources

import com.example.appkotlin.features.recognize.domain.entities.InputImage

interface IRecognizeService {
    fun open();
    fun close();
    fun getWidth(): Int;
    fun getHeight(): Int;
    fun recognize(inputImage: InputImage): Map<String, Double>;
}