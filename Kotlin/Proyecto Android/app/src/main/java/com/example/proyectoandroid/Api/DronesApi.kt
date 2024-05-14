package com.example.proyectoandroid.Api

import com.example.proyectoandroid.Drones
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface DronesApi {


    interface ApiService {

        @GET("Drones")
        fun getDrones(): Call<List<Drones>>

        @POST("Drones")
        fun guardarDron(@Body dron: Drones): Call<Void>

    }
}