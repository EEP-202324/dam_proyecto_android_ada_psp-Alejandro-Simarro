package com.example.applicationdrones

data class Drone(
    val id: Int? = null, // Hacer el parámetro id opcional proporcionando un valor predeterminado
    val name: String,
    val apellido: String,
    val de: String
)

