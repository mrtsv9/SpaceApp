package com.example.space.presentation.base.presenter

import com.example.space.presentation.base.view.BaseMvpView
import moxy.MvpPresenter

abstract class BasePresenter<V : BaseMvpView> internal constructor(
//    protected val schedulerProvider: SchedulerProvider,
//    protected val compositeDisposable: CompositeDisposable
) : MvpPresenter<BaseMvpView>() {



}