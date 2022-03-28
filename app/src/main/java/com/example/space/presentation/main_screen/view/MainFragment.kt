package com.example.space.presentation.main_screen.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.domain.dto.RoverDataResponse
import com.example.space.databinding.FragmentMainBinding
import com.example.space.presentation.base.view.BaseFragment
import com.example.space.presentation.main_screen.interactor.MainPresenter
import com.example.space.presentation.main_screen.repository.MainInteractor
import moxy.ktx.moxyPresenter

class MainFragment: BaseFragment<FragmentMainBinding>(), MainView  {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding
        get() = FragmentMainBinding::inflate

    private val presenter by moxyPresenter { MainPresenter(this, MainInteractor()) }

    override fun setup() {

    }

    override fun displayData(data: List<RoverDataResponse>) {

    }

}