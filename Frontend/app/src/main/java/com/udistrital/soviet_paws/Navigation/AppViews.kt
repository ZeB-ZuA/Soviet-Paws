package com.udistrital.soviet_paws.Navigation

sealed class AppViews(val route: String) {

    object homeScreen : AppViews("home_screen")
    object addPet : AppViews("add_pet_screen")
    object listPets : AppViews("list_pets")




}