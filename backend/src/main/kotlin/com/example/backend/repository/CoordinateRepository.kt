package com.example.backend.repository

import com.example.backend.entity.Coordinate
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CoordinateRepository : JpaRepository<Coordinate, Long> {
}