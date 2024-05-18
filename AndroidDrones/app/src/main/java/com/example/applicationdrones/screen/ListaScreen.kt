package com.example.applicationdrones.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.applicationdrones.model.Drone
import com.example.applicationdrones.viewModel.MyViewModel

@Composable
fun ListaScreen(navController: NavHostController, viewModel: MyViewModel) {
    val drones = viewModel.drones.collectAsState().value

    LazyColumn(
        modifier = Modifier.padding(),
        verticalArrangement = Arrangement.spacedBy(10.dp)

    ) {
        items(drones) { drone ->
            DroneCard(drone, viewModel, navController)
        }
    }
}

@Composable
fun DroneCard(drone: Drone, viewModel: MyViewModel, navController: NavHostController) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "ID: ${drone.id}")
            Text(text = "Precio: $${drone.precio}")
            Text(text = "Color: ${drone.color}")
            Text(text = "Modelo: ${drone.de}")

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = { viewModel.deleteDroneById(drone.id) }) {
                    Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                }

                IconButton(onClick = { expanded = !expanded }) {
                    Icon(Icons.Default.Info, contentDescription = "Especificaciones")
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .padding(8.dp)
                        .width(IntrinsicSize.Min)
                ) {
                    Column() {
                        Text("Especificaciones del dron")
                        Divider()
                        Text(text = "ID: ${drone.id}")
                        Text(text = "Precio: $${drone.precio}")
                        Text(text = "Color: ${drone.color}")
                        Text(text = "Modelo: ${drone.de}")
                    }
                }
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                }
            }
        }
    }
}


