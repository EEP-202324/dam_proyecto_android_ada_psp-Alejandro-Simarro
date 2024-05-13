package com.example.aplicaciondrones

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.aplicaciondrones.UsuariosViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.example.aplicaciondrones.ui.theme.AplicacionDronesTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme


class MainActivity : ComponentActivity() {
    val viewModel: ViewModels<UsuariosViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AplicacionDronesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        viewModel.getUsuarios()
                        ScreenDRON(viewModel._listaUsuarios, viewModel)
                    }

                }
            }
        }
    }
}
@Composable
fun ScreenDRON(listaUsuarios: ArrayList<Usuario>, viewModel: UsuariosViewModel) {

    var id by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var DE by remember { mutableStateOf("") }
    var isEditando by remember { mutableStateOf(false) }
    var textButton by remember { mutableStateOf("Agregar Usuario") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(12.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
        ) {
            Formulario(
                idUsuario = id,
                nombre = nombre,
                apellido = apellido,
                DE = DE,
                funNombre = { nombre = it },
                isEditando = isEditando,
                funIsEditando = { isEditando = false },
                textButton = textButton,
                funTextButton = { textButton = it },
                funResetCampos = {
                    nombre = ""
                    apellido = ""
                    DE = ""
                },
               viewModel = viewModel
            )
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    items(listaUsuarios) { usuario ->
                        CardUsuario(
                            funIdUsuario = {id = it},
                            funNombre = { nombre = it },
                            funApellido = { apellido = it },
                            funDe = { DE = it },
                            funTextButton = { textButton = it },
                            funIsEditando = { isEditando = it },
                            usuario = usuario
                        ) {
                            viewModel.deleteUsuario(usuario.id_usuario)
                        }
                    }
                }
            }
        }
    }
}