package com.example.space.presentation.main_screen.presenter

import android.util.Log
import com.example.space.presentation.main_screen.interactor.MainInteractor
import com.example.space.presentation.main_screen.view.MainFragment
import com.example.space.presentation.main_screen.view.MainView
import com.example.space.presentation.navigation.Screens
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import moxy.MvpPresenter
import moxy.presenterScope
import javax.inject.Inject

class MainPresenter(
    private val mvpView: MainView,
    private val interactor: MainInteractor,
    private val router: Router
): MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
//        fetchRoverData()
        router.newRootScreen(Screens.openMainFragment())
    }

    private fun fetchRoverData() = runBlocking {
//        Log.d("KEK", interactor.fetchRoverData().toString())
    }

}