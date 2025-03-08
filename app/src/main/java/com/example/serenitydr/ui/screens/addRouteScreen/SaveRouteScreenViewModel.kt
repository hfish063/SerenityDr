package com.example.serenitydr.ui.screens.addRouteScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.serenitydr.client.GMPlace
import com.example.serenitydr.client.googleMapsService
import com.example.serenitydr.client.pathAsString
import com.example.serenitydr.client.routeApiService
import com.example.serenitydr.model.Coordinate
import com.example.serenitydr.model.Route
import com.example.serenitydr.request.RouteRequest
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch

data class AddRouteScreenState(
    var isLoading: Boolean = true,
    var route: Route = Route(0, "", "", emptyList()),
    var drawPoints: List<GMPlace> = emptyList(),
    var error: String? = null
)

class SaveRouteScreenViewModel : ViewModel() {
    private val _routeState = mutableStateOf(AddRouteScreenState())
    val routeState: State<AddRouteScreenState> = _routeState

    fun onTitleChange(newT: String) {
        _routeState.value = _routeState.value.copy(
            route = _routeState.value.route.copy(title = newT)
        )
    }

    fun onDescChange(newD: String) {
        _routeState.value = _routeState.value.copy(
            route = _routeState.value.route.copy(description = newD)
        )
    }

    fun addCoord(lat: Double, lng: Double) {
        _routeState.value = _routeState.value.copy(
            route = _routeState.value.route.copy(
                coordinates =

                _routeState.value.route.coordinates.plus(
                    Coordinate(
                        latitude = lat,
                        longitude = lng,
                        order = _routeState.value.route.coordinates.count() + 1,
                        id = 0
                    )
                )
            )
        )
        if (_routeState.value.route.coordinates.count() < 2)
            return
        redraw()
    }

    fun removeCoord() {
        _routeState.value = _routeState.value.copy(
            route = _routeState.value.route.copy(
                coordinates =

                _routeState.value.route.coordinates.dropLast(1)
            )
        )
        if (_routeState.value.route.coordinates.count() < 2) {
            _routeState.value =
                _routeState.value.copy(drawPoints = emptyList())
            return
        }
        redraw()
    }

    fun clearCoords() {
        _routeState.value = _routeState.value.copy(
            route = _routeState.value.route.copy(
                coordinates =

                emptyList(),
            ),
            drawPoints = emptyList()
        )
        redraw()
    }

    private fun redraw() {
        viewModelScope.launch {
            try {
                println("CHECKING MAP")
                val response =
                    googleMapsService.getRoute(pathAsString(_routeState.value.route.coordinates))
                _routeState.value =
                    _routeState.value.copy(drawPoints = response.snappedPoints.toList())
            } catch (e: Exception) {
                setError(e)
            }
        }
    }

    fun polylinePoints(): List<LatLng> {
        val rv = mutableListOf<LatLng>()
        for (pt in _routeState.value.drawPoints) {
            rv.add(LatLng(pt.location.latitude, pt.location.longitude))
        }
        println("Polyline Points: $rv")
        return rv
    }

    fun handleSaveRoute() {
        if (!validTitle())
            return

        viewModelScope.launch {
            try {
                val routeReq = buildRouteRequest()
                routeApiService.saveRoute(routeReq)
                setSuccessfulResult()
            } catch (e: Exception) {
                setError(e)
            }
        }
    }

    private fun validTitle(): Boolean {
        return _routeState.value.route.title.length > 5
    }

    private fun buildRouteRequest(): RouteRequest {
        return RouteRequest(
            title = _routeState.value.route.title,
            description = _routeState.value.route.description,
            coordinates = _routeState.value.route.coordinates
        )
    }

    private fun setSuccessfulResult() {
        _routeState.value =
            _routeState.value.copy(
                isLoading = false,
                route = Route(0, "", "", emptyList()),
                error = null
            )
    }

    private fun setError(e: Exception) {
        _routeState.value = _routeState.value.copy(isLoading = false, error = e.message)
    }
}