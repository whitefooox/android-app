package com.example.appkotlin.features.recognize.domain.entities

data class RecognizeResult(val label: String, val confidence: Double) {
    fun getPercent(): Double{
        return confidence * 100;
    }
}