package com.example.myapplication

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun DroneListItem(drone: Drones) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(text = "ID: ${drone.Id}", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Nombre: ${drone.Nombre}", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Apellido: ${drone.Apellido}", style = MaterialTheme.typography.bodyLarge)
        Text(text = "De: ${drone.de}", style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun DroneList(drones: List<Drones>) {
    Column {
        drones.forEach { drone ->
            DroneListItem(drone = drone)
            Divider()
        }
    }
}

@Composable
fun AddDroneForm(onAddDrone: (Drones) -> Unit) {
    var id by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var de by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        OutlinedTextField(
            value = id,
            onValueChange = { id = it },
            label = { Text("ID") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = apellido,
            onValueChange = { apellido = it },
            label = { Text("Apellido") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = de,
            onValueChange = { de = it },
            label = { Text("De") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val newDrone = Drones(
                    Id = id.toIntOrNull() ?: 0,
                    Nombre = nombre,
                    Apellido = apellido,
                    de = de
                )
                onAddDrone(newDrone)
                // Clear fields after adding drone
                id = ""
                nombre = ""
                apellido = ""
                de = ""
            },
            enabled = id.isNotEmpty() && nombre.isNotEmpty() && apellido.isNotEmpty() && de.isNotEmpty()
        ) {
            Text("Add Drone")
        }
    }
}

data class Drones(val Id: Int, val Nombre: String, val Apellido: String, val de: String)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DroneApp() {
    var drones by remember { mutableStateOf(emptyList<Drones>()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Drones") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // Show dialog or navigate to add drone screen
                }
            ) {
                Text("+")
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AddDroneForm { newDrone ->
                drones = drones + newDrone
            }
            Spacer(modifier = Modifier.height(16.dp))
            DroneList(drones)
        }
    }
}

@Preview
@Composable
fun PreviewDroneApp() {
    DroneApp()
}
