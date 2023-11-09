package com.example.appkotlin.features.recognize.presentation.context

import android.content.Context

object AppContext {
    private lateinit var appContext: Context;

    fun set(context: Context){
        appContext = context;
    }

    fun get(): Context {
        return appContext;
    }
}