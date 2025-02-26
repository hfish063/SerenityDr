package com.example.backend.repository

import com.example.backend.entity.Route
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RouteRepository : JpaRepository<Route, Long> {
}