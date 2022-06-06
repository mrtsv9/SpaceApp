package com.example.space.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.activity.OnBackPressedCallback
import com.example.space.R
import com.example.space.databinding.ActivityMainBinding
import com.example.space.presentation.navigation.Screens
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatActivity
import javax.inject.Inject

@Suppress("DEPRECATION")
@AndroidEntryPoint
class MainActivity : MvpAppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private var navigator: AppNavigator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        navigator = AppNavigator(this, binding?.container?.id!!)

        navigator?.applyCommands(arrayOf(Replace(Screens.openMainFragment())))

        bottomNavigation()

        val backCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Snackbar.make(
                    binding!!.root,
                    "Do you want to exit?", Snackbar.LENGTH_SHORT
                )
                    .setAction("Exit") {
                        finish()
                    }.show()
            }
        }
        onBackPressedDispatcher.addCallback(this, backCallback)

    }

    private fun bottomNavigation() {
        binding?.bottomNav?.setOnItemSelectedListener { item ->

            when (item.itemId) {
                R.id.mi_home -> {
                    navigator?.applyCommands(arrayOf(Replace(Screens.openMainFragment())))
                }
                R.id.mi_map -> {
                    navigator?.applyCommands(arrayOf(Replace(Screens.openMapFragment())))
                }
            }
            true
        }
    }

    override fun onResume() {
        super.onResume()
        navigator?.let { navigatorHolder.setNavigator(it) }
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

}
