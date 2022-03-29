package com.example.space.presentation.main_screen.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.domain.dto.RoverDataResponse
import com.example.space.databinding.FragmentMainBinding
import com.example.space.presentation.base.view.BaseFragment
import com.example.space.presentation.main_screen.presenter.MainPresenter
import com.example.space.presentation.main_screen.interactor.MainInteractor
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class MainFragment: BaseFragment<FragmentMainBinding>(), MainView  {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding
        get() = FragmentMainBinding::inflate

    @Inject lateinit var interactor: MainInteractor

    private val presenter by moxyPresenter { MainPresenter(this, interactor) }

    override fun setup() {
        presenter.fetchRoverData()
    }

    override fun displayData(data: List<RoverDataResponse>) {

    }

}