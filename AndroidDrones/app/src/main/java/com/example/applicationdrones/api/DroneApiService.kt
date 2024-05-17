package com.example.applicationdrones.api


import com.example.applicationdrones.model.Drone
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface DroneApiService {
    // Otras funciones existentes...

    @GET("drones/{id}")
    suspend fun getDroneById(@Path("id") id: Int): Response<Drone>

    @DELETE("drones/{id}")
    suspend fun deleteDroneById(@Path("id") id: Int): Response<Unit>

    @GET("drones")
    suspend fun getDrones(): Response<List<Drone>>

    @POST("drones")
    suspend fun createDrone(@Body drone: Drone): Response<Drone>
}

val retrofit = Retrofit.Builder()
    .baseUrl("http://10.0.2.2:8080/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

object  ApiClient {
    val service: DroneApiService by lazy {
        retrofit.create(DroneApiService::class.java)
    }
}

