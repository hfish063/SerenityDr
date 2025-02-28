package com.example.serenitydr.client

import com.example.serenitydr.model.RouteModel
import com.example.serenitydr.request.RouteRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

val routeApiService: RouteApiService =
    ApiClient.buildService(serviceClass = RouteApiService::class.java)

interface RouteApiService {
    @GET("/routes/all")
    fun getAllRoutes(): Response<List<RouteModel>>

    @Headers("Content-Type: application/json")
    @POST("/routes/save")
    fun saveRoute(@Body routeRequest: RouteRequest): Response<RouteModel>
}