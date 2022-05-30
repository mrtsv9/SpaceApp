package com.example.space.presentation.main_screen.interactor

import android.util.Log
import com.example.domain.dto.RoverDataResponse
import com.example.domain.service.ApiService
import com.example.space.presentation.base.repository.BaseInteractor
import com.example.space.presentation.main_screen.model.toRoverDataItems
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import rx.schedulers.Schedulers
import rx.schedulers.Schedulers.io
import javax.inject.Inject

class MainInteractor @Inject constructor(
    private val apiService: ApiService
) : BaseInteractor {

    suspend fun fetchRoverData(): RoverDataResponse? {

        apiService.getRoverData()
            .observeOn(Schedulers.computation())
            .subscribe({
                Log.d("KEK", it.body().toString())
            }, {
                Log.d("KEK", it.toString())
            }
            )
//        Log.d("KEK", qwe.toString())

//        var data: RoverDataResponse? = null
//        apiService.getRoverData()
//            .subscribeOn(Schedulers.computation())
//            .observeOn(Schedulers.io())
//            .subscribe {
//                data = it
//            }
//        return data
        return null
    }
}
