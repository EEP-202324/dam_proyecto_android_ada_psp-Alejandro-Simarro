package com.example.applicationdrones

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class MyViewModel : ViewModel() {

    // Ejecuta la función fetchData en un contexto de Kotlin Coroutine en un hilo secundario
    fun fetchData() {
        viewModelScope.launch {
            try {
                val drones = service.getDrones()
                // Aquí puedes manejar la lista de drones recibida
                println("Lista de drones: $drones")

                val newDrone = Drone(id = 1, name = "Nombre", apellido = "Apellido", de = "Origen")
                val response = service.createDrone(newDrone)
                if (response.isSuccessful) {
                    // El drone se creó exitosamente
                    println("El drone se creó exitosamente")
                } else {
                    // Ocurrió un error al crear el drone
                    println("Ocurrió un error al crear el drone: ${response.message()}")
                }
            } catch (e: Exception) {
                // Maneja el error aquí
                println("Error en fetchData: ${e.message}")
            }
        }
    }
}
