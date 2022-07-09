package com.example.space.presentation

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.space.R
import com.example.space.databinding.ActivityMainBinding
import com.example.space.presentation.navigation.ChainHolder
import com.example.space.presentation.navigation.ChainScreen
import com.example.space.presentation.navigation.Screens
import com.example.space.presentation.notifications.NotificationsReceiver
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatActivity
import java.lang.ref.WeakReference
import java.util.ArrayList
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : MvpAppCompatActivity(), ChainHolder {

    private var binding: ActivityMainBinding? = null

    override val chain = ArrayList<WeakReference<Fragment>>()

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator: Navigator = object : AppNavigator(this, R.id.container) {

        override fun applyCommands(commands: Array<out Command>) {
            super.applyCommands(commands)
            fragmentManager.executePendingTransactions()
            printScreensScheme()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

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

        if (savedInstanceState == null) {
            navigator.applyCommands(arrayOf<Command>(Replace(Screens.openMainFragment())))
        } else {
            printScreensScheme()
        }

        IntentFilter(Intent.ACTION_POWER_CONNECTED).also {
            registerReceiver(NotificationsReceiver(), it)
        }

    }

    private fun bottomNavigation() {
        binding?.bottomNav?.setOnItemSelectedListener { item ->

            when (item.itemId) {
                R.id.mi_home -> {
                    navigator.applyCommands(arrayOf(Replace(Screens.openMainFragment())))
                }
                R.id.mi_map -> {
                    navigator.applyCommands(arrayOf(Replace(Screens.openMapFragment())))
                }
            }
            true
        }
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    private fun printScreensScheme() {
        val fragments = ArrayList<ChainScreen>()
        for (fragmentReference in chain) {
            val fragment = fragmentReference.get()
            if (fragment != null && fragment is ChainScreen) {
                fragments.add(fragment)
            }
        }
        fragments.sortWith { f1, f2 ->
            val t = f1.creationTime - f2.creationTime
            if (t > 0) 1 else if (t < 0) -1 else 0
        }
        val keys = ArrayList<String>()
        for (fragment in fragments) {
            keys.add(fragment.name)
        }
    }

}