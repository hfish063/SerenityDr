package com.example.serenitydr.request

import com.example.serenitydr.model.Coordinate

data class RouteRequest(
    val title: String,
    val description: String?,
    val coordinates: List<Coordinate>,
    val uid: String = "UID"
) {
}