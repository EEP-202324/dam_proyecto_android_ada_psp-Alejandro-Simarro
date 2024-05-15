package com.example.proyectoandroid

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import android.os.Bundle
import androidx.compose.material.Text
import com.example.proyectoandroid.ui.theme.ProyectoAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Aquí se muestra la interfaz de formulario de drones
                    DronesFormulario(onGuardarClick = { /* Lógica para guardar el dron */ })
                }
            }
        }
    }
}

@Composable
fun DronesFormulario(onGuardarClick: () -> Unit) {
    // Aquí va el código del formulario de drones
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProyectoAndroidTheme {
        // Ejemplo de un texto dentro de la vista previa
        Surface {
            Text("Hola desde la vista previa")
        }
    }
}
