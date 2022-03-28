package com.example.space.presentation.base.presenter

import com.example.space.presentation.base.view.BaseMvpView

interface BaseMvpPresenter<V : BaseMvpView> {

    fun onAttach(view: V?)

    fun onDetach()

    fun getView(): V?

}