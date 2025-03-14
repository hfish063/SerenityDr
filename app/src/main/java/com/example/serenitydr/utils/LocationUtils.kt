package com.example.serenitydr.utils

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.example.serenitydr.ui.screens.addRouteScreen.LocationViewModel
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng

class LocationUtils(private val context: Context) {
    private val _fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    fun updateCurrentLocation(
        locationViewModel: LocationViewModel
    ) {
        val accuracy = Priority.PRIORITY_BALANCED_POWER_ACCURACY

        if (isConnected()) {
            val locationRequest = CurrentLocationRequest.Builder().setPriority(accuracy).build()
            
            _fusedLocationClient.getCurrentLocation(locationRequest, null)
                .addOnSuccessListener { location ->
                    if (location != null) {
                        val latLng = LatLng(location.latitude, location.longitude)
                        locationViewModel.updateLocation(latLng)
                    }
                }
        }
    }

    fun isConnected(): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) && locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    fun hasLocationPermission(): Boolean {
        return hasPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) && hasPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    }

    private fun hasPermission(context: Context, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }
}