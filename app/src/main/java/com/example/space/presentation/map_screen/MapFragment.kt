package com.example.space.presentation.map_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.example.space.R
import com.example.space.databinding.FragmentMapBinding
import com.example.space.presentation.base.view.BaseFragment
import com.google.android.material.snackbar.Snackbar

class MapFragment : BaseFragment<FragmentMapBinding>(), MapView {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMapBinding
        get() = FragmentMapBinding::inflate

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

    }

}