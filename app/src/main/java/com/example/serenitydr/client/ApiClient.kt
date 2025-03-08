package com.example.serenitydr.client

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val backendApiClient = ApiClient()
val googleApiClient = ApiClient(baseUrl = "https://roads.googleapis.com/v1/")

class ApiClient(private val baseUrl: String = "http://10.0.2.2:8080/api/") {
    private val loggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Builds an API service class allowing for http request communication.
     *
     * Usage: Get an implementation of a service Interface with request methods.  Use this object to
     * make API requests, e.g. 'apiRouteService.getAllRoutes()'.  Retrofit will handle implementation
     * details for provided method 'getAllRoutes()' in the original Interface.
     *
     * @param serviceClass This is the service interface that Retrofit will implement for you.
     */
    fun <T> buildService(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }
}