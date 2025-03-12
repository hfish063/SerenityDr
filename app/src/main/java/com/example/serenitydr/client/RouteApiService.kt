package com.example.serenitydr.client

import com.example.serenitydr.model.Route
import com.example.serenitydr.request.RouteRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

val routeApiService: RouteApiService =
    backendApiClient.buildService(serviceClass = RouteApiService::class.java)

interface RouteApiService {
    @GET("routes/all")
    suspend fun findAllRoutes(): Response<List<Route>>

    @GET("routes/{routeId}")
    suspend fun findRouteById(@Path("routeId") routeId: Long): Response<Route>

    @Headers("Content-Type: application/json")
    @POST("routes/save")
    suspend fun saveRoute(@Body routeRequest: RouteRequest): Response<Route>
}