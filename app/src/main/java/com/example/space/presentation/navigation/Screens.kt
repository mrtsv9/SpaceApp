package com.example.space.presentation.navigation

import android.content.Intent
import com.example.space.presentation.MainActivity
import com.example.space.presentation.main_screen.view.MainFragment
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {

    fun openMainActivity() = ActivityScreen { Intent(it, MainActivity::class.java) }

    fun openMainFragment() = FragmentScreen { MainFragment() }

}