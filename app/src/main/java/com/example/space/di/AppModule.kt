package com.example.space.di

import com.example.domain.service.ApiService
import com.example.space.presentation.main_screen.repository.MainRepository
import com.example.space.presentation.map_screen.presenter.MapPresenter
import com.example.space.presentation.map_screen.repository.MapRepository
import com.example.space.presentation.map_screen.view.MapView
import com.example.space.presentation.splash_screen.presenter.SplashPresenter
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideMainRepository(apiService: ApiService): MainRepository = MainRepository(apiService)

    @Provides
    @Singleton
    fun provideMapRepository(): MapRepository = MapRepository()

    @Provides
    fun provideMapPresenter(repository: MapRepository): MapPresenter = MapPresenter(repository)

}