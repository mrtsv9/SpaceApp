package com.example.space.presentation.main_screen.presenter

import com.example.space.presentation.main_screen.repository.MainRepository
import com.example.space.presentation.main_screen.model.toRoverDataItems
import com.example.space.presentation.main_screen.view.MainView
import com.example.space.presentation.navigation.Screens
import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.MvpPresenter

class MainPresenter(
    private val router: Router,
    private val mvpView: MainView,
    private val repository: MainRepository
) : MvpPresenter<MainView>() {

    private var disposable = CompositeDisposable()

    fun fetchRoverData() {
        disposable.add(repository.fetchRoverData()
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