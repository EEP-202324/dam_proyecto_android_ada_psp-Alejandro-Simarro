package com.example.applicationdrones


import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface DroneApiService {
    @GET("Drones")
    suspend fun getDrones(): List<Drone>

    @POST("Drones")
    suspend fun createDrone(@Body drone: Drone): Response<Drone>
}

val retrofit = Retrofit.Builder()
    .baseUrl("http://10.0.2.2:8080/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

object  ApiClient {
    val service: DroneApiSevice by lazy {
        retrofit.create(DroneApiSevice::class.java)
    }
}

class DroneApiSevice {

}
