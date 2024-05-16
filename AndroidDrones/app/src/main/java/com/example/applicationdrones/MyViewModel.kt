package com.example.applicationdrones

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MyViewModel(private val service: DroneApiService) : ViewModel() {

    // Función para obtener la lista de drones
    fun getDrones(onSuccess: (List<Drone>) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val drones = service.getDrones()
                onSuccess(drones)
            } catch (e: Exception) {
                onError(e.message ?: "Error al obtener la lista de drones")
            }
        }
    }

    // Función para crear un nuevo drone
    fun createDrone(drone: Drone, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = service.createDrone(drone)
                if (response.isSuccessful) {
                    onSuccess()
                } else {
                    onError("Error al crear el drone: ${response.message()}")
                }
            } catch (e: Exception) {
                onError(e.message ?: "Error al crear el drone")
            }
        }
    }
}
