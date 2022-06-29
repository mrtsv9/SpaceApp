package com.example.space.presentation.details_screen.presenter

import com.example.space.presentation.details_screen.view.DetailsView
import com.example.space.presentation.navigation.Screens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class DetailsPresenter(
    private val router: Router,
    private val mvpView: DetailsView,
): MvpPresenter<DetailsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        mvpView.displayData()
    }

    fun onBackPressed() {
        router.navigateTo(Screens.openMainFragment())
    }

}