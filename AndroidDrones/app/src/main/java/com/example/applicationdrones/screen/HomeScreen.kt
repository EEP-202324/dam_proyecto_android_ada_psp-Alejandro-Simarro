package com.example.applicationdrones.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.applicationdrones.viewModel.MyViewModel

@Composable
fun HomeScreen(navController: NavHostController, viewModel: MyViewModel) {

    LaunchedEffect(key1 = true) {
        viewModel.getDrones()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(onClick = { navController.navigate("Lista") }) {
            Text(text = "Obtener drones")
        }

        Button(onClick = { navController.navigate("Formulario") }) {
            Text(text = "Agregar drone")
        }
    }
}

