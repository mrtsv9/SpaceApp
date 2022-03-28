package com.example.space.presentation.main_screen.repository

import com.example.domain.dto.RoverDataResponse
import com.example.domain.service.ApiService
import com.example.space.presentation.base.repository.BaseInteractor
import kotlinx.coroutines.runBlocking
import retrofit2.Response

class MainInteractor(
    private val apiService: ApiService
): BaseInteractor {

    fun fetchRoverData(): List<RoverDataResponse>? = runBlocking {
        return@runBlocking apiService.getRoverData().body()
    }

}