package com.example.backend.dto

interface BaseDto<T> {
    fun toEntity(): T
}