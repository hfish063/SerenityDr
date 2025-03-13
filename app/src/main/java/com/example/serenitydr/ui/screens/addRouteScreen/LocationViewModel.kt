package com.example.serenitydr.ui.screens.addRouteScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng

data class LocationData(private val latitude: Double, private val longitude: Double)

class LocationViewModel : ViewModel() {
    private val _location = mutableStateOf<LatLng?>(null)
    val location = _location

    fun updateLocation(newLocation: LatLng) {
        _location.value = newLocation
    }
}