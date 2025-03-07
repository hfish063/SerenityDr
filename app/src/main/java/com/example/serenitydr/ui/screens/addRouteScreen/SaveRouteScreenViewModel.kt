package com.example.serenitydr.ui.screens.addRouteScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.serenitydr.model.Coordinate
import com.example.serenitydr.model.Route

data class AddRouteScreenState(
    var isLoading: Boolean = true,
    var route: Route = Route(0, "", "", emptyList()),
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
        println(routeState.value.route.description)
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
    }

    fun handleSaveRoute() {
        //Validation for route data
        println("SAVING")
        if (_routeState.value.route.title.length < 5)
            return

        //SEND DATA HERE

    }
}