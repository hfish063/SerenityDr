package com.example.backend.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*

@Entity
@Table(name = "coordinates")
class Coordinate(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,

        @Column(name = "latitude", nullable = false)
        var latitude: Double,

        @Column(name = "longitude", nullable = false)
        var longitude: Double,

        @Column(name = "position", nullable = false)
        var order: Int,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "route_id", nullable = false)
        @JsonBackReference
        var route: Route
) {}
