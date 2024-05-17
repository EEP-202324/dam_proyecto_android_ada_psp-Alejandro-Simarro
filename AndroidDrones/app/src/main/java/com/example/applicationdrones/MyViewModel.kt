package com.example.applicationdrones

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applicationdrones.ApiClient.service
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {
    private val _drones = MutableStateFlow<List<Drone>>(emptyList())
    val drones: StateFlow<List<Drone>> = _drones.asStateFlow()

    // Función para obtener la lista de drones
    fun getDrones() {
        viewModelScope.launch {
            try {
                val response = service.getDrones()
                if (response.isSuccessful) {
                    _drones.value = response.body()!!
                    Log.d("UserViewModel", "Drones fetched: ${_drones.value}")
                } else {
                    Log.e(
                        "UserViewModel",
                        "Error fetching drones: ${response.errorBody()?.string()}"
                    )
                }
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error fetching drones:", e)
            }
        }
    }

    // Función para crear un nuevo drone
    fun createDrone(drone: Drone, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = service.createDrone(drone)
                if (response.isSuccessful) {
                    _drones.value += response.body()!!
                    onSuccess()
                } else {
                    onError("Error: ${response.code()} ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("API Error", "Fallo de red: ${e.localizedMessage}", e)
                onError("Fallo de red: ${e.localizedMessage}")
            }
        }
    }
}
