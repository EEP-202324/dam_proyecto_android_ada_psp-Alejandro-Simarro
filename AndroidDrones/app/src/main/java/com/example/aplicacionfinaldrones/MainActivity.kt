package com.example.aplicacionfinaldrones

import android.os.Bundle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8080") // Reemplaza con la URL de tu backend
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val droneApiService = retrofit.create(DroneApiService::class.java)

    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Tu lógica de UI y composición aquí
        fetchData()
    }

    private fun fetchData() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val drones = droneApiService.getDrones()
                // Maneja los drones obtenidos aquí (puedes actualizar la UI si es necesario)
            } catch (e: Exception) {
                // Maneja cualquier error de red
            }
        }
    }
}
