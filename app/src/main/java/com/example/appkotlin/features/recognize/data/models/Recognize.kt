package com.example.appkotlin.features.recognize.data.models

import com.example.appkotlin.features.recognize.domain.entities.RecognizeResult

class Recognize {
    companion object {
        fun fromMap(map: Map<String, Double>): List<RecognizeResult> {
            var results= map.entries.map {
                RecognizeResult(it.key, it.value);
            }
            results = results.sortedByDescending { it.confidence }
            return results;
        }
    }
}