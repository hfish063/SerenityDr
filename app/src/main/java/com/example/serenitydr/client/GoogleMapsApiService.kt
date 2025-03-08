package com.example.serenitydr.client

import com.example.serenitydr.model.Coordinate
import retrofit2.http.GET
import retrofit2.http.Query

val googleMapsService: GoogleMapsApiService =
    googleApiClient.buildService(serviceClass = GoogleMapsApiService::class.java)

interface GoogleMapsApiService {
    @GET("snapToRoads")
    suspend fun getRoute(
        @Query("path") path: String,
        @Query("interpolate") interpolate: Boolean = true,
        @Query("key") key: String = ""
    ): GMSnappedPoints
}

data class GMlatlng(
    val latitude: Double,
    val longitude: Double
)

data class GMPlace(
    val location: GMlatlng,
    val placeId: String
)

data class GMSnappedPoints(
    val snappedPoints: List<GMPlace>
)


fun pathAsString(coords: List<Coordinate>): String {
    var path = ""

    for (coord in coords) {
        path += coord.latitude.toString() + "," + coord.longitude.toString() + "|"
    }

    return path.dropLast(1)
}