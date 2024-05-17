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
                    Log.e("Error: ${response.code()} ${response.message()}", "" )
                }
            } catch (e: Exception) {
                Log.e("API Error", "Fallo de red: ${e.localizedMessage}", e)
            }
        }
    }
    // Función para obtener un dron por su ID
    fun getDroneById(id: Int) {
        viewModelScope.launch {
            try {
                val response = service.getDroneById(id)
                if (response.isSuccessful) {
                    // Manejar el dron obtenido
                    val drone = response.body()
                    // Por ejemplo, imprimir el dron obtenido
                    drone?.let { Log.d("MyViewModel", "Drone obtenido: $it") }
                } else {
                    Log.e("MyViewModel", "Error al obtener el dron: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("MyViewModel", "Error al obtener el dron:", e)
            }
        }
    }

    // Función para eliminar un dron por su ID
    fun deleteDroneById(id: Int) {
        viewModelScope.launch {
            try {
                val response = service.deleteDroneById(id)
                if (response.isSuccessful) {
                    // Manejar la eliminación exitosa del dron
                    Log.d("MyViewModel", "Dron eliminado exitosamente")
                    // También podrías actualizar la lista de drones después de eliminar
                } else {
                    Log.e("MyViewModel", "Error al eliminar el dron: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("MyViewModel", "Error al eliminar el dron:", e)
            }
        }
    }


}
