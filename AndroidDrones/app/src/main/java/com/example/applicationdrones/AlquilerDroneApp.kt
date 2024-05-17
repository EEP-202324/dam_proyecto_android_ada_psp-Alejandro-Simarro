package com.example.applicationdrones

import android.net.wifi.hotspot2.pps.HomeSp
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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

//@Composable
//fun AlquilerDroneApp() {
//    val navController = rememberNavController()
//    ApplicationDronesTheme {
//        val viewModel: MyViewModel = viewModel()
//        Scaffold { innerPadding ->
//            NavHost(
//                navController = navController,
//                startDestination = "Home",
//                modifier = Modifier.padding(innerPadding)
//            ) {
//                composable("Home") {
//                    HomeScreen(navController, viewModel)
//                }
//                composable("Formulario") {
//                    FormularioScreen(navController)
//                }
//                composable("Lista") {
//                    ListaScreen(navController)
//                }
//            }
//        }
//    }
//
//}





