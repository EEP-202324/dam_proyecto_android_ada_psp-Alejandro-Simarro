package com.example.applicationdrones.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.applicationdrones.model.Drone
import com.example.applicationdrones.viewModel.MyViewModel

@Composable
fun ListaScreen(navController: NavHostController, viewModel: MyViewModel) {
    val drones =  viewModel.drones.collectAsState().value

    LazyColumn(
        modifier = Modifier.padding(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(drones) { drone ->
            DroneCard(drone, navController)
        }
    }
}


@Composable
fun DroneCard(drone: Drone, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(text = "ID: ${drone.id}")
            Text(text = "Precio: $${drone.precio}")
            Text(text = "Color: ${drone.color}")
            Text(text = "De: ${drone.de}")

            // Agrega aquí cualquier otro campo que quieras mostrar en la tarjeta
        }
    }
}

