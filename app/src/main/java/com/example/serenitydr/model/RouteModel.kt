package com.example.serenitydr.model

data class RouteModel(
    val id: Long,
    val title: String,
    val description: String?,
    val uid: String = "UID"
) {
}