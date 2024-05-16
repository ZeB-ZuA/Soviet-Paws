package com.udistrital.soviet_paws.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.udistrital.soviet_paws.views.AddPets
import com.udistrital.soviet_paws.views.HomeScreen
import com.udistrital.soviet_paws.views.ListPets

@Composable
fun AppNav() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppViews.homeScreen.route) {
        composable(route = AppViews.homeScreen.route) {
            HomeScreen(navController)
        }
        composable(route = AppViews.addPet.route){
            AddPets(navController = navController)
        }
        composable(route=AppViews.listPets.route){
            ListPets(navController = navController)
        }



    }
}