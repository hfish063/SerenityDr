package com.example.serenitydr.client

import com.example.serenitydr.model.Coordinate
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

val googleMapsService: GoogleMapsApiService =
    googleApiClient.buildService(serviceClass = GoogleMapsApiService::class.java)

interface GoogleMapsApiService {
    @GET("snapToRoads")
    suspend fun getRoute(
        @Query("path") path: String,
        @Query("key") key: String = ""
    ): Response<List<GMPlace>>
}

data class GMlatlng(
    val latitude: Double,
    val longitude: Double
)

data class GMPlace(
    val location: GMlatlng,
    val placeId: String
)

fun pathAsString(coords: List<Coordinate>): String {
    var path = ""

    for (coord in coords) {
        path += coord.latitude.toString() + "," + coord.longitude.toString() + "|"
    }

    return path.dropLast(1)
}