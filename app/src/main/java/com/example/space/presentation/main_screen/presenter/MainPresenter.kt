package com.example.space.presentation.main_screen.presenter

import android.util.Log
import android.widget.Toast
import com.example.space.presentation.main_screen.interactor.MainInteractor
import com.example.space.presentation.main_screen.model.toRoverDataItems
import com.example.space.presentation.main_screen.view.MainView
import com.example.space.presentation.navigation.Screens
import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter

class MainPresenter(
    private val router: Router,
    private val mvpView: MainView,
    private val interactor: MainInteractor
) : MvpPresenter<MainView>() {

    private var disposable = CompositeDisposable()

    fun fetchRoverData() {
        disposable.add(interactor.fetchRoverData()
            .subscribeOn(Schedulers.io())
            .observeOn(mainThread())
            .subscribe({
                mvpView.displayData(it.toRoverDataItems())
            }, {

            })
        )
    }

    fun navigateToDetailsScreen(itemImgLink: String) {
        router.navigateTo(Screens.openDetailsFragment(itemImgLink))
    }

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }

}