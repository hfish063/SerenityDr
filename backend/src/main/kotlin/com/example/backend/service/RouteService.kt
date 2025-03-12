package com.example.backend.service

import com.example.backend.dto.RouteDto
import com.example.backend.entity.Coordinate
import com.example.backend.entity.Route
import com.example.backend.error.EntityNotFoundException
import com.example.backend.repository.CoordinateRepository
import com.example.backend.repository.RouteRepository
import org.springframework.stereotype.Service

@Service
class RouteService(private val routeRepository: RouteRepository, private val coordinateRepository: CoordinateRepository) {
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

   fun saveRoute(route: RouteDto): Route {
        val savedRoute = routeRepository.save(Route(title = route.title, description = route.description, uid = route.uid))
        val coordinateEntities: MutableList<Coordinate> = mutableListOf()

        route.coordinates.forEach { coordinateDto ->
            coordinateEntities += Coordinate(latitude = coordinateDto.latitude,
                    longitude = coordinateDto.longitude, order = coordinateDto.order, route = savedRoute)
        }
        coordinateRepository.saveAll(coordinateEntities)

        return savedRoute
    }
}