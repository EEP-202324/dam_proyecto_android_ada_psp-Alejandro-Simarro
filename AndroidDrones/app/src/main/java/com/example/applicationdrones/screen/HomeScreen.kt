import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
    // Iniciar la carga de drones al entrar en la pantalla
    LaunchedEffect(key1 = true) {
        viewModel.getDrones()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "¡Bienvenido!",
                style = MaterialTheme.typography.headlineLarge
            )

            Button(
                onClick = { navController.navigate("Lista") },
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Ver Drones")
            }

            Button(
                onClick = { navController.navigate("Formulario") },
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Agregar Drone")
            }
        }
    }
}
