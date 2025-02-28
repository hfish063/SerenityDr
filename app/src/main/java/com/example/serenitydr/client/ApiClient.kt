package com.example.serenitydr.client

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val baseUrl: String = "http://10.0.2.2:8080/api/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val routeApiService = retrofit.create(RouteApiService::class.java)
    val coordinateApiService = null
}