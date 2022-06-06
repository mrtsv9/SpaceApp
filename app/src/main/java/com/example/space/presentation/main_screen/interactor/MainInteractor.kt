package com.example.space.presentation.main_screen.interactor

import com.example.domain.dto.RoverDataResponse
import com.example.domain.service.ApiService
import com.example.space.presentation.base.repository.BaseInteractor
import com.example.space.presentation.main_screen.model.RoverDataItem
import com.example.space.presentation.main_screen.model.toRoverDataItems
import io.reactivex.Single
import kotlinx.coroutines.*
import javax.inject.Inject

class MainInteractor @Inject constructor(
    private val apiService: ApiService
) : BaseInteractor {

    fun fetchRoverData(): Single<RoverDataResponse> {
        return apiService.getRoverData()
    }
//        return Single.create {
//            CoroutineScope(Dispatchers.IO).launch {
//                it.onSuccess(apiService.getRoverData())
//            }
//        }
//    }

}
