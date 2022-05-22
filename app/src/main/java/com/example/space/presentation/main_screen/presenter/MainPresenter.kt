package com.example.space.presentation.main_screen.presenter

import com.example.space.presentation.main_screen.interactor.MainInteractor
import com.example.space.presentation.main_screen.model.toRoverDataItems
import com.example.space.presentation.main_screen.view.MainView
import kotlinx.coroutines.launch
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