package com.example.domain.service

import com.example.domain.dto.RoverDataResponse
import retrofit2.http.GET
import rx.Observable

interface ApiService {

    @GET("rovers/curiosity/photos?sol=1000&api_key=duSrXQJcfbwbadQgkXtlebLhZCZcgkdYh3lzoI5L")
    suspend fun getRoverData(): Observable<RoverDataResponse>

}