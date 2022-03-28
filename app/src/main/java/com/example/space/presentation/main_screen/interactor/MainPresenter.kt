package com.example.space.presentation.main_screen.interactor

import com.example.space.presentation.main_screen.repository.MainInteractor
import com.example.space.presentation.main_screen.view.MainView
import moxy.MvpPresenter

class MainPresenter(
    private val mvpView: MainView,
    private val interactor: MainInteractor
): MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        
    }

    fun fetchRoverData() {
        val response = interactor.fetchRoverData()
        response?.let { viewState.displayData(it) }
    }

}