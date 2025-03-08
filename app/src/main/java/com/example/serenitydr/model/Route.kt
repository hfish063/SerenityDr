package com.example.serenitydr.model

data class Route(
    var id: Long,
    var title: String,
    var description: String?,
    var coordinates: List<Coordinate>,
    var uid: String = "UID"
) {
}