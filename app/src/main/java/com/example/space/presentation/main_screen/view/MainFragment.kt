package com.example.space.presentation.main_screen.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.dto.RoverDataResponse
import com.example.space.databinding.FragmentMainBinding
import com.example.space.presentation.base.view.BaseFragment
import com.example.space.presentation.main_screen.presenter.MainPresenter
import com.example.space.presentation.main_screen.interactor.MainInteractor
import com.example.space.presentation.main_screen.model.RoverDataItem
import com.github.terrakok.cicerone.Router
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import moxy.ktx.moxyPresenter
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(), MainView {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding
        get() = FragmentMainBinding::inflate

    @Inject
    lateinit var interactor: MainInteractor

    private val presenter by moxyPresenter { MainPresenter(this, interactor) }

    override fun setup() {

        presenter.fetchRoverData()

        val backCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Snackbar.make(
                    binding.root,
                    "Do you want to exit?", Snackbar.LENGTH_SHORT
                )
                    .setAction("Exit") {
                        activity?.finish()
                    }.show()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, backCallback)

    }

    override fun displayData(data: List<RoverDataItem>) {
        val adapter = RoverDataAdapter(data)
        binding.rvPhotos.layoutManager = GridLayoutManager(context, 2)
        binding.rvPhotos.adapter = adapter
    }

}