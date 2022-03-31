package com.example.space.presentation.main_screen.view

import com.example.domain.dto.RoverDataResponse
import com.example.space.presentation.base.view.BaseMvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface MainView: BaseMvpView {

    @AddToEndSingle
    fun displayData(data: RoverDataResponse)

}