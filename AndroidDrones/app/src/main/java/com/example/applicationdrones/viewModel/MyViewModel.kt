package com.example.applicationdrones.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.applicationdrones.api.ApiClient.service
import com.example.applicationdrones.model.Drone
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {
    private val _drones = MutableStateFlow<List<Drone>>(emptyList())
    val drones: StateFlow<List<Drone>> = _drones.asStateFlow()

    fun getDrones() {
        viewModelScope.launch {
            try {
                val response = service.getDrones()
                if (response.isSuccessful) {
                    _drones.value = response.body() ?: emptyList()
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
    fun createDrone(drone: Drone) {
        viewModelScope.launch {
            try {
                val response = service.createDrone(drone)
                if (response.isSuccessful) {
                    _drones.value += response.body()!!
                } else {
                    Log.e("Error: ${response.code()} ${response.message()}", "")
                }
            } catch (e: Exception) {
                Log.e("API Error", "Fallo de red: ${e.localizedMessage}", e)
            }
        }
    }

    // Función para eliminar un dron por su ID
    fun deleteDroneById(id: Int) {
        viewModelScope.launch {
            try {
                val response = service.deleteDroneById(id)
                if (response.isSuccessful) {
                    // Eliminar el dron de la lista
                    _drones.value = _drones.value.filterNot { it.id == id }
                    Log.d("MyViewModel", "Dron eliminado exitosamente")
                } else {
                    Log.e(
                        "MyViewModel",
                        "Error al eliminar el dron: ${response.errorBody()?.string()}"
                    )
                }
            } catch (e: Exception) {
                Log.e("MyViewModel", "Error al eliminar el dron:", e)
            }
        }
    }
}


