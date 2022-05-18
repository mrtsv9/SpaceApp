package com.example.space.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.space.R
import com.example.space.databinding.ActivityMainBinding
import com.example.space.presentation.navigation.Screens
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.androidx.AppNavigator
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatActivity
import javax.inject.Inject

@Suppress("DEPRECATION")
@AndroidEntryPoint
class MainActivity : MvpAppCompatActivity() {

    private var binding: ActivityMainBinding? = null

//    @Inject
//    lateinit var interactor: MainInteractor
//
//    @Inject
//    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private var navigator: AppNavigator? = null

//    private val presenter by moxyPresenter { MainPresenter(this, interactor) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        navigator = AppNavigator(this, binding?.container?.id!!)

        Handler().postDelayed({
            navigator?.applyCommands(arrayOf(Replace(Screens.openMainFragment())))
        }, 2000)

        bottomNavigation()

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
