package com.example.space.di

import android.app.Application
import com.example.domain.service.ApiService
import com.example.space.presentation.main_screen.interactor.MainInteractor
import com.github.terrakok.cicerone.Cicerone
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@HiltAndroidApp
class HiltApp: Application() {

}
