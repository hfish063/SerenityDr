package com.example.serenitydr.ui.screens.listAllRoutesScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.serenitydr.client.routeApiService
import com.example.serenitydr.model.Route
import kotlinx.coroutines.launch
import retrofit2.Response

class ListAllRoutesViewModel : ViewModel() {
    var routes by mutableStateOf<List<Route>>(emptyList())
        private set

    var isLoading by mutableStateOf(true)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    init {
        fetchRoutes()
    }

    public fun fetchRoutes() {
        viewModelScope.launch {
            try {
                val response: Response<List<Route>> = routeApiService.findAllRoutes()
                if (response.isSuccessful) {
                    response.body()?.let {
                        routes = it
                    }
                    errorMessage = null
                } else {
                    errorMessage = "Error: ${response.message()}"
                }
            } catch (e: Exception) {
                errorMessage = "Exception: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }
}