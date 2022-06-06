package com.example.space.presentation.details_screen.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.space.databinding.FragmentDetailsBinding
import com.example.space.presentation.base.view.BaseFragment

class DetailsFragment: BaseFragment<FragmentDetailsBinding>(), DetailsView {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailsBinding
        get() = FragmentDetailsBinding::inflate

    override fun setup() {

    }

    override fun displayData() {
        TODO("Not yet implemented")
    }
}