package com.example.myapplication

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET





const val URL = "http://10.0.2.2:8080/"

interface ConsumirApi {
    @GET("Drones")
    fun getCoger(): Call<List<Drones>>

    object Servicio {
        val instancia: ConsumirApi

        init {
            val retrofit = Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            instancia = retrofit.create(ConsumirApi::class.java)
        }
    }
}
