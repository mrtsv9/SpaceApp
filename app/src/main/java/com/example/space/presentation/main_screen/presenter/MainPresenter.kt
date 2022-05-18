package com.example.space.presentation.main_screen.presenter

import android.util.Log
import com.example.space.presentation.main_screen.interactor.MainInteractor
import com.example.space.presentation.main_screen.model.toRoverDataItems
import com.example.space.presentation.main_screen.view.MainFragment
import com.example.space.presentation.main_screen.view.MainView
import com.example.space.presentation.navigation.Screens
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import moxy.MvpPresenter
import moxy.presenterScope

class MainPresenter(
    private val mvpView: MainView,
    private val interactor: MainInteractor
): MvpPresenter<MainView>() {

    fun fetchRoverData() {
        presenterScope.launch {
            val data = interactor.fetchRoverData()?.toRoverDataItems()
            data?.let { mvpView.displayData(it) }
        }

    }

}