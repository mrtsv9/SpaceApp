package com.example.space.di

import android.app.Application
import com.example.domain.service.ApiService
import com.example.space.presentation.main_screen.interactor.MainInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@HiltAndroidApp
class MyApp: Application()


@Module
@InstallIn(ActivityComponent::class)
object AppModule {

//    @Provides
//    fun providesBaseUrl() : String = "https://api.nasa.gov/mars-photos/api/v1/"
//
//    @Provides
//    @Singleton
//    fun provideRetrofit(BASE_URL : String) : Retrofit = Retrofit.Builder()
//        .addConverterFactory(GsonConverterFactory.create())
//        .baseUrl(BASE_URL)
//        .build()


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    fun provideMainInteractor(apiService: ApiService): MainInteractor = MainInteractor(apiService)


}