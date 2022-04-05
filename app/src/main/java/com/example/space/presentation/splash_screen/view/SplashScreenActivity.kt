package com.example.space.presentation.splash_screen.view

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.example.space.databinding.ActivitySplashBinding
import com.example.space.presentation.navigation.Screens
import com.example.space.presentation.splash_screen.presenter.SplashPresenter
import com.github.terrakok.cicerone.*
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
@Suppress("DEPRECATION")
@AndroidEntryPoint
class SplashScreenActivity : MvpAppCompatActivity(), SplashView {

    private var binding: ActivitySplashBinding? = null

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator: Navigator = AppNavigator(this, -1)

    private val presenter by moxyPresenter { SplashPresenter(router) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        Handler().postDelayed({
            if (!checkForInternet(applicationContext)) {
                Snackbar.make(
                    this, binding!!.root, "No Internet Connection!",
                    Snackbar.LENGTH_LONG
                ).show()
            } else {
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent)
//                navigatorHolder.setNavigator(navigator)
//                presenter.openMainActivity()
                navigator.applyCommands(arrayOf(Replace(Screens.openMainActivity())))
//                router.replaceScreen(Screens.openMainActivity())
            }
        }, 1000)

    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    private fun checkForInternet(context: Context): Boolean {

        // register activity with the connectivity manager service
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // if the android version is equal to M or greater we need to use the
        // NetworkCapabilities to check what type of network has the internet connection
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // Returns a Network object corresponding to the currently active default data network.
            val network = connectivityManager.activeNetwork ?: return false

            // Representation of the capabilities of an active network.
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                // Indicates this network uses a Wi-Fi transport, or WiFi has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                // Indicates this network uses a Cellular transport, or
                // Cellular has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                // else return false
                else -> false
            }
        } else {
            // if the android version is below M
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            return networkInfo.isConnected
        }
    }

}