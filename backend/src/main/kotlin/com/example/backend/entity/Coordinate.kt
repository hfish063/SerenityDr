package com.example.backend.entity

import jakarta.persistence.*

@Entity
@Table(name = "coordinates")
class Coordinate(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0,

    @Column(name = "latitude", nullable = "false")
    var latitude: Double,

    @Column(name = "longitude", nullable = "false")
    var longitude: Double,

    @Column(name = "order", nullable = "false")
    var order: Int,

    @Column(name = "route_id", nullable = "false")
    var route_id: Long
) {}
