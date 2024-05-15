package com.example.myapplication


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

@Composable
fun Screen1(navController: NavController, viewModel: DronesViewModel) {
    val dronesList by viewModel.dronesList.observeAsState(emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Lista de Drones", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar la lista de drones obtenidos de la base de datos
        DroneList(dronesList)

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("screen2") },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Añadir Nuevo Dron")
        }
    }
}

@Composable
fun DroneList(drones: List<Drones>) {
    LazyColumn {
        items(drones) { drone ->
            DroneItem(drone)
        }
    }
}

@Composable
fun DroneItem(drone: Drones) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "ID: ${drone.id}")
            Text(text = "Apellido: ${drone.apellido}")
            Text(text = "Nombre: ${drone.name}")
            Text(text = "DE: ${drone.de}")
        }
    }
}

@Composable
fun Screen2(navController: NavController, viewModel: DronesViewModel) {
    // Aquí se debería implementar la lógica para añadir un nuevo drone a la lista
    val newDrone = remember { mutableStateOf(Drones(0, "", "", "")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Añadir Nuevo Dron", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        // Formulario para agregar un nuevo dron
        DroneForm(newDrone.value) { drone ->
            viewModel.addDrone(drone)
            navController.popBackStack()
        }
    }
}

@Composable
fun DroneForm(drone: Drones, onSubmit: (Drones) -> Unit) {
    // Implementa un formulario para agregar un nuevo dron
    // Puedes usar TextField, Button, etc. para permitir al usuario ingresar los datos del dron
}

// ViewModel para manejar la lógica de los drones
class DronesViewModel : ViewModel() {
    private val _dronesList = MutableLiveData<List<Drones>>()
    val dronesList: LiveData<List<Drones>> = _dronesList

    fun loadDrones() {
        // Aquí cargarías los datos de la base de datos y los asignarías a _dronesList
    }

    fun addDrone(drone: Drones) {
        // Aquí agregarías un nuevo dron a la base de datos
        // Después de agregarlo, actualiza la lista de drones llamando a loadDrones()
    }
}
