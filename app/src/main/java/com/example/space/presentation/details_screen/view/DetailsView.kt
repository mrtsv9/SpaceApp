package com.example.space.presentation.details_screen.view

import com.example.space.presentation.base.view.BaseMvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface DetailsView: BaseMvpView {

    @AddToEndSingle
    fun displayData()

}