package com.example.applicationdrones


import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface DroneApiService {
    @GET("drones")
    suspend fun getDrones(): List<Drone>

    @POST("drones")
    suspend fun createDrone(@Body drone: Drone): Response<Drone>
}

val retrofit = Retrofit.Builder()
    .baseUrl("http://localhost:8080/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val service = retrofit.create(DroneApiService::class.java)
