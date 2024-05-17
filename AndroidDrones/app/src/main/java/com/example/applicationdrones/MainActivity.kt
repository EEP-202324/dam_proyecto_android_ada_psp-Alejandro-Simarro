package com.example.applicationdrones

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.applicationdrones.screen.FormularioScreen
import com.example.applicationdrones.screen.HomeScreen
import com.example.applicationdrones.screen.ListaScreen
import com.example.applicationdrones.ui.theme.ApplicationDronesTheme
import com.example.applicationdrones.viewModel.MyViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApplicationDronesTheme {
                val viewModel: MyViewModel = viewModel()
                val navController = rememberNavController()
                Scaffold { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "Home",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("Home") {
                            HomeScreen(navController, viewModel)
                        }
                        composable("Formulario") {
                            FormularioScreen(navController)
                        }
                        composable("Lista") {
                            ListaScreen(navController, viewModel)
                        }
                    }
                }
            }
        }
    }
}
