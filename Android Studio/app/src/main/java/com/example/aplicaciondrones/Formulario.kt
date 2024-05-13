package com.example.aplicaciondrones


import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.aplicaciondrones.UsuariosViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Formulario(
    idUsuario: String,
    nombre: String,
    apellido: String,
    DE: String,
    funNombre: (String) -> Unit,
    funApellido: (String) -> Unit,
    funDE: (String) -> Unit,
    isEditando: Boolean,
    funIsEditando: () -> Unit,
    textButton: String,
    funTextButton: (String) -> Unit,
    funResetCampos: () -> Unit,
    onAddUsuario: (Usuario) -> Unit,
    onUpdateUsuario: (String, Usuario) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = nombre,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        singleLine = true,
        maxLines = 1,
        onValueChange = { funNombre(it) },
        label = { Text(text = "Nombre") }
    )
    Spacer(modifier = Modifier.padding(vertical = 8.dp))
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            if (isEditando) {
                onUpdateUsuario(
                    idUsuario,
                    Usuario(
                        id_usuario = idUsuario,
                        nombre = nombre,
                        apellido = apellido,
                        DE = DE
                    )
                )
                funTextButton("Agregar Usuario")
                funIsEditando()
            } else {
                onAddUsuario(
                    Usuario(
                        id_usuario = "",
                        nombre = nombre,
                        apellido = apellido,
                        DE = DE
                    )
                )
            }
            funResetCampos()
        }
    ) {
        Text(text = textButton)
    }
}