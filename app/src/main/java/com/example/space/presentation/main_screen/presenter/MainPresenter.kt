package com.example.space.presentation.main_screen.presenter

import com.example.space.presentation.main_screen.interactor.MainInteractor
import com.example.space.presentation.main_screen.view.MainView
import kotlinx.coroutines.runBlocking
import moxy.MvpPresenter
import moxy.presenterScope

class MainPresenter(
    private val mvpView: MainView,
    private val interactor: MainInteractor
): MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        fetchRoverData()
        val q = 3
    }

    fun fetchRoverData() = runBlocking {
        val response = interactor.fetchRoverData()
        response?.let { viewState.displayData(it) }
    }

}