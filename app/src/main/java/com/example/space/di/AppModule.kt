package com.example.space.di

import com.example.domain.service.ApiService
import com.example.space.presentation.main_screen.interactor.MainInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(ActivityComponent::class)
object AppModule {

    @Provides
    fun provideMainInteractor(apiService: ApiService): MainInteractor = MainInteractor(apiService)

}