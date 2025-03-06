package com.example.serenitydr.ui.screens.viewRouteScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.serenitydr.model.Route

data class ViewRouteScreenState(
    val isLoading: Boolean = true,
    val route: Route? = null,
    val error: String? = null
)

class ViewRouteScreenViewModel : ViewModel() {
    private val _routeState = mutableStateOf(ViewRouteScreenState())
    val routeState: State<ViewRouteScreenState> = _routeState

    init {
        fetchRoute()
    }

    private fun fetchRoute() {
        // TODO: Fetch route by id from ApiService class method
    }
}