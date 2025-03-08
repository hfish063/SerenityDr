package com.example.serenitydr.ui.screens.viewRouteScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.serenitydr.client.routeApiService
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

    fun getRouteTitle(): String {
        return _routeState.value.route?.title ?: ""
    }

    fun getRouteDescription(): String {
        return _routeState.value.route?.description ?: ""
    }

    private fun fetchRoute() {
        viewModelScope.launch {
            try {
                val response = routeApiService.findRouteById(1).body()
                setSuccessfulResponse(response)
            } catch (e: Exception) {
                setError(e)
            }
        }
    }

    private fun setSuccessfulResponse(response: Route?) {
        _routeState.value =
            _routeState.value.copy(isLoading = false, route = response, error = null)
    }

    private fun setError(e: Exception) {
        _routeState.value = _routeState.value.copy(isLoading = false, error = e.message)
    }
}