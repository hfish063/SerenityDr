package com.example.serenitydr.client

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * ApiClient object contains the service classes for our backend endpoints.
 *
 * Usage: Get an implementation of a service Interface using the buildService() method.
 * Use this object to make API requests, e.g. 'apiRouteService.getAllRoutes()'
 */
object ApiClient {
    // '10.0.2.2' is used to access our machines localhost from within the Android emulator
    private const val baseUrl: String = "http://10.0.2.2:8080/api/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> buildService(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }
}