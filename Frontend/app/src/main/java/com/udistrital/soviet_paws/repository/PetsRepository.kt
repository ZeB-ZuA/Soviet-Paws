package com.udistrital.soviet_paws.repository

import com.udistrital.soviet_paws.models.Pet

interface PetsRepository {

    fun save(pet: Pet)
    fun list():List<Pet>
    fun filterByNameAndSort(name: String?, sortBy: String?): List<Pet>





}