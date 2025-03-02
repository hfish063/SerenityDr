package com.example.backend.controller

import com.example.backend.dto.RouteDto
import com.example.backend.entity.Route
import com.example.backend.service.RouteService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/routes")
class RouteController(private val routeService: RouteService) {
    @GetMapping("/all")
    fun getAllRoutes(): List<Route> {
        return routeService.getAllRoutes()
    }

    @GetMapping("/{id}")
    fun getRouteById(@PathVariable id: Long): Route {
        return routeService.getRouteById(id)
    }

    @PostMapping("/save")
    fun addRoute(@RequestBody routeRequest: RouteDto): Route {
        return routeService.saveRoute(routeRequest.toEntity())
    }
}