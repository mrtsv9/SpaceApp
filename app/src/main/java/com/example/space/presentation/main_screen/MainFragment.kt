package com.example.space.presentation.main_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.space.databinding.FragmentMainBinding
import com.example.space.presentation.base.view.BaseFragment

class MainFragment: BaseFragment<FragmentMainBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding
        get() = FragmentMainBinding::inflate

    override fun setup() {

    }


}