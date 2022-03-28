package com.example.space.presentation.main_screen.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.space.databinding.FragmentMainBinding
import com.example.space.presentation.base.view.BaseFragment
import com.example.space.presentation.main_screen.presenter.MainPresenter
import moxy.PresenterBinder
import moxy.PresenterStore

class MainFragment: BaseFragment<FragmentMainBinding>(), MainView  {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding
        get() = FragmentMainBinding::inflate

    private var presenter: MainPresenter? = null

    override fun setup() {
        presenter = MainPresenter()

    }


}