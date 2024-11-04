package com.example.ufps_eventos.navigation

sealed class AppScreens(val route: String) {
    object LoginScreen: AppScreens("login_screen")
    object MainScreen: AppScreens("main_screen")
    object FirstScreen: AppScreens("first_screen")
    object SecondScreen: AppScreens("second_screen")
}