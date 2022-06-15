package com.example.space.presentation.details_screen.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.space.R
import com.example.space.databinding.FragmentDetailsBinding
import com.example.space.presentation.base.view.BaseFragment
import com.example.space.presentation.details_screen.presenter.DetailsPresenter
import com.example.space.presentation.details_screen.view.onboarding.OnboardingDialog
import com.github.terrakok.cicerone.Router
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    override fun setup() {

        val dialog = OnboardingDialog()
        dialog.show(childFragmentManager, "MyDialog")

        binding.icBack.setOnClickListener { presenter.onBackPressed() }

        val backCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                presenter.onBackPressed()
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(this, backCallback)
    }

    override fun onStop() {
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        super.onStop()
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