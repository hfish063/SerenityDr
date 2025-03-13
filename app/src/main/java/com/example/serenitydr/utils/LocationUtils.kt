package com.example.serenitydr.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import android.util.Log
import com.example.serenitydr.ui.screens.addRouteScreen.LocationViewModel
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng

class LocationUtils(private val context: Context, var initialRequestSent: Boolean = false) {
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    fun updateCurrentLocation(
        locationViewModel: LocationViewModel
    ) {
        val accuracy = Priority.PRIORITY_BALANCED_POWER_ACCURACY

        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                locationResult.lastLocation?.let {
                    Log.d(
                        "GPS",
                        "LOCATION ${it.latitude}, ${it.longitude}"
                    ) // See if onLocationResult() is being called

                    val location = LatLng(it.latitude, it.longitude)
                    locationViewModel.updateLocation(location)
                }
            }
        }

        val locationRequest = LocationRequest.Builder(
            accuracy, 5000
        ).build()

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
        initialRequestSent = true
    }
}