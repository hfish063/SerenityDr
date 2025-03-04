package com.example.serenitydr.model

data class Route(
    val id: Long,
    val title: String,
    val description: String?,
    val coordinates: List<Coordinate>,
    val uid: String = "UID"
) {
}