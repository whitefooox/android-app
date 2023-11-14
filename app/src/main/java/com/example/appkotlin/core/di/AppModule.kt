package com.example.appkotlin.core.di

import android.content.Context
import com.example.appkotlin.features.recognize.data.datasources.IRecognizeService
import com.example.appkotlin.features.recognize.data.datasources.TFClassificator
import com.example.appkotlin.features.recognize.data.repositories.RecognizeRepository
import com.example.appkotlin.features.recognize.domain.dependencies.IRecognizeRepository
import com.example.appkotlin.features.recognize.domain.services.RecognizeUseCase
import com.example.appkotlin.features.recognize.presentation.presenter.InputImagePresenter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideRecognizeService(@ApplicationContext context: Context): IRecognizeService {
        return TFClassificator(context);
    }

    @Provides
    fun provideRecognizeRepository(recognizeService: IRecognizeService): IRecognizeRepository {
        return RecognizeRepository(recognizeService);
    }

    @Provides
    fun provideRecognizeUseCase(recognizeRepository: IRecognizeRepository): RecognizeUseCase {
        return RecognizeUseCase(recognizeRepository);
    }

    @Provides
    fun provideImageInputPresenter(@ApplicationContext context: Context): InputImagePresenter{
        return InputImagePresenter(context);
    }
}