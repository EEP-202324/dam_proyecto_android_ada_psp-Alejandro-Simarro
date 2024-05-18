package com.example.applicationdrones.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.applicationdrones.viewModel.MyViewModel


@Composable
fun HomeScreen(navController: NavHostController, viewModel: MyViewModel) {
    // Cargar los drones al entrar en la pantalla
    LaunchedEffect(key1 = true) {
        viewModel.getDrones()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { navController.navigate("Lista") },
            modifier = Modifier
                .padding(vertical = 8.dp)
                .height(72.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Obtener drones", style = MaterialTheme.typography.bodyLarge)
        }

        Button(
            onClick = { navController.navigate("Formulario") },
            modifier = Modifier
                .padding(vertical = 8.dp)
                .height(72.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Agregar drone", style = MaterialTheme.typography.bodyLarge)
        }

    }
}