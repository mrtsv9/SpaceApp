package com.example.space.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.domain.RetrofitInstance
import com.example.domain.dto.RoverDataResponse
import com.example.domain.service.ApiService
import com.example.space.databinding.ActivityMainBinding
import com.example.space.presentation.main_screen.interactor.MainInteractor
import com.example.space.presentation.main_screen.presenter.MainPresenter
import com.example.space.presentation.main_screen.view.MainFragment
import com.example.space.presentation.main_screen.view.MainView
import com.example.space.presentation.navigation.Screens
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import moxy.MvpActivity
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import java.lang.ref.WeakReference
import java.util.ArrayList
import javax.inject.Inject

@Suppress("DEPRECATION")
@AndroidEntryPoint
class MainActivity : MvpAppCompatActivity(), MainView {

    private var binding: ActivityMainBinding? = null

    @Inject
    lateinit var interactor: MainInteractor

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private var navigator: AppNavigator? = null

    private val presenter by moxyPresenter { MainPresenter(this, interactor, router) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        navigator = AppNavigator(this, binding?.container?.id!!)

        Handler().postDelayed({
            navigator?.applyCommands(arrayOf(Replace(Screens.openMainFragment())))
        }, 2000)

//        supportFragmentManager.beginTransaction()
//            .add(binding?.container?.id!!, MainFragment())
//            .commit()
//        Log.d("KEK", apiService.getRoverData().toString())
//        val test = RetrofitInstance.getRetrofitInstance().create(ApiService::class.java)
//        lifecycleScope.launch {
//            Log.d("KEK", test.getRoverData().body().toString())
//        }

    }

    override fun onResume() {
        super.onResume()
        navigator?.let { navigatorHolder.setNavigator(it) }
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun displayData(data: RoverDataResponse) {

    }


}
