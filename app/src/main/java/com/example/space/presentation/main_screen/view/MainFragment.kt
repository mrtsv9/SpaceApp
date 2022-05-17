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
//
//    @Inject
//    lateinit var router: Router

    private val presenter by moxyPresenter { MainPresenter(this, interactor) }

    override fun setup() {

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

        presenter.fetchRoverData()

    }

    override fun displayData(data: RoverDataResponse) {
        val listItems = emptyList<RoverDataItem>().toMutableList()

        data.photos.forEach {
            listItems.add(RoverDataItem(it.imgLink, it.camera.fullName, it.rover.name))
        }
        Log.d("KEK", listItems.toString())

        val adapter = RoverDataAdapter(listItems)
        binding.rvPhotos.layoutManager = GridLayoutManager(context, 2)
        binding.rvPhotos.setHasFixedSize(true)
//        binding.rvPhotos.layoutManager = LinearLayoutManager(
//            binding.root.context,
//            LinearLayoutManager.VERTICAL, false
//        )
        binding.rvPhotos.adapter = adapter
    }

}