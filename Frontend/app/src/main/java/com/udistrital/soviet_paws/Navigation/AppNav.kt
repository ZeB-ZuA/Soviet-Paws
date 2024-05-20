package com.udistrital.soviet_paws.Navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.udistrital.soviet_paws.models.Pet
import com.udistrital.soviet_paws.views.AddPets
import com.udistrital.soviet_paws.views.HomeScreen
import com.udistrital.soviet_paws.views.ListPets
import com.udistrital.soviet_paws.views.PetDetails

@Composable
fun AppNav() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppViews.homeScreen.route) {
        composable(route = AppViews.homeScreen.route) {
            HomeScreen(navController)
        }
        composable(route = AppViews.addPet.route) {
            AddPets(navController = navController)
        }
        composable(route = AppViews.listPets.route) {
            ListPets(navController = navController)
        }
        composable(route =  AppViews.petDetails.route + "/{name}/{type}/{age}/{breed}") { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val age = arguments.getString("age")?.toInt()
            val name = arguments.getString("name")
            val type = arguments.getString("type")
            val breed = arguments.getString("breed")
            PetDetails(navController = navController, age=age, name = name, type = type, breed = breed)
        }


    }
}