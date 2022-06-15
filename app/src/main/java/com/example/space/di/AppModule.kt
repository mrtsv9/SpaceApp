package com.example.space.di

import com.example.domain.service.ApiService
import com.example.space.presentation.main_screen.repository.MainRepository
import com.example.space.presentation.splash_screen.presenter.SplashPresenter
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object AppModule {

    @Provides
    fun provideMainInteractor(apiService: ApiService): MainRepository = MainRepository(apiService)

    @Provides
    fun provideSplashPresenter(router: Router): SplashPresenter = SplashPresenter(router)

}