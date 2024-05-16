package com.example.applicationdrones

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.applicationdrones.ui.theme.ApplicationDronesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApplicationDronesTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "droneScreen") {
                    composable("droneScreen") {
                        DroneScreen(navController)
                    }
                    composable("postForm") {
                        PostForm(navController)
                    }
                }
            }
        }
    }
}

@Composable
fun DroneScreen(navController: NavHostController) {
    var showPostForm by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(onClick = { /* Realizar solicitud GET */ }) {
            Text(text = "Obtener drones")
        }

        if (showPostForm) {
            // Muestra el formulario de envío
            navController.navigate("postForm")
        } else {
            Button(onClick = { showPostForm = true }) {
                Text(text = "Agregar drone")
            }
        }
    }
}

@Composable
fun PostForm(navController: NavHostController) {
    var name by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var de by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )
        TextField(
            value = apellido,
            onValueChange = { apellido = it },
            label = { Text("Apellido") },
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
            // Aquí podrías realizar la lógica de enviar los datos
            // Por ejemplo, llamar a una función que maneje la solicitud POST
            // sendDroneData(name, apellido, de)

            // Regresar a la pantalla anterior
            navController.popBackStack()
        }) {
            Text(text = "Enviar")
        }
    }
}
