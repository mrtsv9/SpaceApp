package com.example.space.presentation.details_screen.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.bumptech.glide.Glide
import com.example.space.databinding.FragmentDetailsBinding
import com.example.space.presentation.base.view.BaseFragment
import com.example.space.presentation.details_screen.presenter.DetailsPresenter
import com.example.space.presentation.main_screen.interactor.MainInteractor
import com.example.space.presentation.main_screen.presenter.MainPresenter
import com.github.terrakok.cicerone.Router
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import moxy.ktx.moxyPresenter
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>(), DetailsView {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailsBinding
        get() = FragmentDetailsBinding::inflate

    @Inject
    lateinit var router: Router

    private val presenter by moxyPresenter { DetailsPresenter(router, this) }

    private val itemImgLink: String
        get() = requireArguments().getString(KEY).toString()

    override fun setup() {
        val backCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                presenter.onBackPressed()
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(this, backCallback)
    }

    override fun displayData() {
        Glide.with(requireActivity())
            .load(itemImgLink)
            .into(binding.customView)
    }

    companion object {

        private const val KEY = "details"

        fun getNewInstance(itemImgLink: String) =
            DetailsFragment().apply {
                arguments = Bundle().apply { putString(KEY, itemImgLink) }
            }

    }
}