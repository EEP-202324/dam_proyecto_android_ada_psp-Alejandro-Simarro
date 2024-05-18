package com.example.applicationdrones.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.applicationdrones.model.Drone
import com.example.applicationdrones.viewModel.MyViewModel

@Composable
fun FormularioScreen(navController: NavHostController) {
    val viewModel: MyViewModel = viewModel()
    var precio by remember { mutableStateOf("") }
    var color by remember { mutableStateOf("") }
    var de by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = precio,
            onValueChange = { precio = it },
            label = { Text("Precio") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )
        TextField(
            value = color,
            onValueChange = { color = it },
            label = { Text("Color") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )
        TextField(
            value = de,
            onValueChange = { de = it },
            label = { Text("DE") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
        )

        Button(onClick = {
            var newDrone = Drone(0, precio, color, de)
            viewModel.createDrone(newDrone)
        }) {
            Text(text = "Enviar")
        }
    IconButton(onClick = { navController.popBackStack() }) {
        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
    }
}

}