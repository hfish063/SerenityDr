package com.example.serenitydr.client

import com.example.serenitydr.model.RouteModel
import retrofit2.Response
import retrofit2.http.GET

interface RouteApiService {
    @GET("/routes/all")
    fun getAllRoutes(): Response<List<RouteModel>>
}