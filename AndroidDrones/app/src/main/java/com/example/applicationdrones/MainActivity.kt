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
import com.example.applicationdrones.ui.theme.ApplicationDronesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApplicationDronesTheme {
                // Llama a la función App que define la interfaz de usuario
                App()
            }
        }
    }
}

@Composable
fun App() {
    DroneScreen()
}

@Composable
fun PostForm(onSubmit: (String, String, String) -> Unit) {
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

        Button(onClick = { onSubmit(name, apellido, de) }) {
            Text(text = "Enviar")
        }
    }
}

@Composable
fun DroneScreen() {
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
            // Llama a la función para realizar la solicitud POST
            myFunction()
        } else {
            Button(onClick = { showPostForm = true }) {
                Text(text = "Agregar drone")
            }
        }
    }
}

@Composable
fun myFunction() {
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

        Button(onClick = { /* Llamar a la función para realizar la solicitud POST */ }) {
            Text(text = "Enviar")
        }
    }
}
