package com.example.serenitydr.ui.screens.addRouteScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng

class LocationViewModel : ViewModel() {
    private val _location = mutableStateOf<LatLng>(
        LatLng(
            34.2164,
            -119.0376
        )
    )
    val location = _location

    fun updateLocation(newLocation: LatLng) {
        _location.value = newLocation
    }
}