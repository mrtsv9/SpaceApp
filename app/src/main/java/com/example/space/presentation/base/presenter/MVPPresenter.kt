package com.example.space.presentation.base.presenter

import com.example.space.presentation.base.view.MVPView

interface MVPPresenter<V : MVPView> {

    fun onAttach(view: V?)

    fun onDetach()

    fun getView(): V?

}