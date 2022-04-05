package com.example.space.presentation.main_screen.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.domain.dto.RoverDataResponse
import com.example.space.databinding.FragmentMainBinding
import com.example.space.presentation.base.view.BaseFragment
import com.example.space.presentation.main_screen.presenter.MainPresenter
import com.example.space.presentation.main_screen.interactor.MainInteractor
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.AndroidEntryPoint
import moxy.ktx.moxyPresenter
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment: BaseFragment<FragmentMainBinding>(), MainView  {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding
        get() = FragmentMainBinding::inflate

    @Inject
    lateinit var interactor: MainInteractor

    @Inject
    lateinit var router: Router

    private val presenter by moxyPresenter { MainPresenter(this, interactor, router) }

    override fun setup() {

    }

    override fun displayData(data: RoverDataResponse) {

    }

}