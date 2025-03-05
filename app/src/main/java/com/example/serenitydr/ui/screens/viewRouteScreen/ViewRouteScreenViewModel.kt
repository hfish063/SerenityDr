package com.example.serenitydr.ui.screens.viewRouteScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.serenitydr.model.Route
import kotlinx.coroutines.launch

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
        viewModelScope.launch {
            try {
                // TODO: Fetch route by id from ApiService class method
                val response = null
                _routeState.value = _routeState.value.copy(isLoading = false, route = response)
            } catch (e: Exception) {
                _routeState.value = _routeState.value.copy(isLoading = false, error = e.message)
            }
        }
    }
}