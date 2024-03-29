package com.example.space.presentation.main_screen.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.space.databinding.FragmentMainBinding
import com.example.space.presentation.base.view.BaseFragment
import com.example.space.presentation.main_screen.presenter.MainPresenter
import com.example.space.presentation.main_screen.repository.MainRepository
import com.example.space.presentation.main_screen.model.RoverDataItem
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.AndroidEntryPoint
import moxy.ktx.moxyPresenter
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(), MainView {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding
        get() = FragmentMainBinding::inflate

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var repository: MainRepository

    private val presenter by moxyPresenter { MainPresenter(router, this, repository) }

    override fun setup() {
        presenter.fetchRoverData()
    }

    override fun displayData(data: List<RoverDataItem>) {
        val adapter = RoverDataAdapter(data) { clickListener(it) }
        binding.rvPhotos.layoutManager = GridLayoutManager(context, 2)
        binding.rvPhotos.adapter = adapter
    }

    private fun clickListener(itemImgLink: String) {
        presenter.navigateToDetailsScreen(itemImgLink)
    }

}