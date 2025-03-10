package com.example.backend.dto

class RouteDto(val title: String, val description: String?, val uid: String, val coordinates: MutableList<CoordinateDto> = mutableListOf()) {
}