package com.example.backend.entity

import jakarta.persistence.*

@Entity
@Table(name = "routes")
class Route(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,

        @Column(name = "title", nullable = false)
        var title: String,

        @Column(name = "description", nullable = true)
        var description: String?,

        @Column(name = "uid", nullable = false)
        var uid: String
) {}
