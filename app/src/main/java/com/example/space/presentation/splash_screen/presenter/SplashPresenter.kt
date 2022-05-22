package com.example.space.presentation.splash_screen.presenter

import com.example.space.presentation.navigation.Screens
import com.example.space.presentation.splash_screen.view.SplashView
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpPresenter
import javax.inject.Inject


class SplashPresenter(private val router: Router): MvpPresenter<SplashView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.navigateTo(Screens.openMainActivity())
    }

}