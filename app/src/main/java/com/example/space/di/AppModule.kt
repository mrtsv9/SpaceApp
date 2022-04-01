package com.example.space.di

import com.example.domain.service.ApiService
import com.example.space.presentation.main_screen.interactor.MainInteractor
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
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

    @Provides
    @Singleton
    fun provideCicerone(): Cicerone<Router> = Cicerone.create()

    @Provides
    @Singleton
    fun provideRouter(cicerone: Cicerone<Router>) = cicerone.router

    @Provides
    @Singleton
    fun getNavigationHolder(cicerone: Cicerone<Router>) = cicerone.getNavigatorHolder()


}