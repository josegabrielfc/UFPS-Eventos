package com.example.ufps_eventos.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ufps_eventos.ui.screens.FirstScreen
import com.example.ufps_eventos.ui.screens.LoginScreen
import com.example.ufps_eventos.ui.screens.MainScreen
import com.example.ufps_eventos.ui.screens.SecondScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.LoginScreen.route){
        composable(route = AppScreens.LoginScreen.route){
            LoginScreen(navController)
        }
        composable(route = AppScreens.MainScreen.route){
            MainScreen(navController)
        }
        composable(route = AppScreens.FirstScreen.route){
            FirstScreen(navController)
        }
        composable(route = AppScreens.SecondScreen.route + "/{text}", arguments = listOf(navArgument(name = "text"){
            type = NavType.StringType
        })){
            SecondScreen(navController, it.arguments?.getString("text"))
        }
    }
}