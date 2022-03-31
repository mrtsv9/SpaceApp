package com.example.space.presentation.main_screen.presenter

import android.util.Log
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
//        val q = 3
    }

    private fun fetchRoverData() = runBlocking {
        Log.d("KEK", interactor.fetchRoverData().toString())
//        response?.let { viewState.displayData(it) }
    }

}