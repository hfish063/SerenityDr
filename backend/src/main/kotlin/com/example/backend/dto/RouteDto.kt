package com.example.backend.dto

import com.example.backend.entity.Route

data class RouteDto(
        private val title: String,
        private val description: String?,
        private val uid: String
) : BaseDto<Route> {
    override fun toEntity(): Route {
        return Route(title = this.title, description = this.description, uid = this.uid)
    }
}