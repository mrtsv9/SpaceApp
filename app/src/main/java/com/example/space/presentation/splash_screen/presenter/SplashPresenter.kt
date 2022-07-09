package com.example.space.presentation.splash_screen.presenter

import com.example.space.presentation.navigation.Screens
import com.example.space.presentation.splash_screen.view.SplashView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter


class SplashPresenter(private val router: Router): MvpPresenter<SplashView>() {

    fun openMainActivity() {
        router.navigateTo(Screens.openMainActivity())
    }

}