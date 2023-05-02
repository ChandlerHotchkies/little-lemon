package com.example.littlelemon

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(items: List<MenuItemRoom>) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = OnBoarding.route
    ) {
        composable(Home.route) {
            Home(navController = navController, items)
        }
        composable(Profile.route) {
            Profile(navController = navController)
        }
        composable(OnBoarding.route) {
            OnBoarding(navController = navController)
        }


    }
}