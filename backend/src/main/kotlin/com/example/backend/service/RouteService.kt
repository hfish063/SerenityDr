package com.example.backend.service

import com.example.backend.entity.Route
import com.example.backend.error.EntityNotFoundException
import com.example.backend.repository.RouteRepository
import org.springframework.stereotype.Service

@Service
class RouteService(private val routeRepository: RouteRepository) {
    fun getAllRoutes(): List<Route> {
        return routeRepository.findAll()
    }

    fun getRouteById(routeId: Long): Route {
        val result = routeRepository.findById(routeId)

        if (result.isEmpty) {
            throw EntityNotFoundException()
        }

        return result.get()
    }

    fun saveRoute(route: Route): Route {
        return routeRepository.save(route)
    }
}