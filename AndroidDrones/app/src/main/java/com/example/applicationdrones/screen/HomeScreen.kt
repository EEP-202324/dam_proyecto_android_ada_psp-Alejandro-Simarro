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
            verticalArrangement = Arrangement.spacedBy(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Alquilar drones",
                style = MaterialTheme.typography.headlineLarge
            )

            Button(
                onClick = { navController.navigate("Lista") },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp)
            ) {
                Text(text = "Ver Drones", style = MaterialTheme.typography.bodyLarge)
            }

            Button(
                onClick = { navController.navigate("Formulario") },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp)
            ) {
                Text(text = "Agregar Drone", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
