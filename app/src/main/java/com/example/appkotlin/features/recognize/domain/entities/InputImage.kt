package com.example.appkotlin.features.recognize.domain.entities

import java.nio.ByteBuffer

data class InputImage(val byteBuffer: ByteBuffer, val width: Int, val height: Int) {}