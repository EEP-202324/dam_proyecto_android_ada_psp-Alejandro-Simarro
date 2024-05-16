package com.example.aplicacionfinaldrones

import retrofit2.http.GET

interface DroneApiService {
    @GET("drones")
    suspend fun getDrones(): List<Drone>
}
