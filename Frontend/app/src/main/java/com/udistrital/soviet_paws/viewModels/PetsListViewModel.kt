package com.udistrital.soviet_paws.viewModels

import PetService
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udistrital.soviet_paws.models.Pet
import com.udistrital.soviet_paws.network.RetrofitClient
import kotlinx.coroutines.launch

class PetsListViewModel(private val context: Context) : ViewModel() {

    private lateinit var petsRepository: PetService
    private val _petsLiveData = MutableLiveData<List<Pet>>()
    val petsLiveData: LiveData<List<Pet>> = _petsLiveData

    init {
        petsRepository = PetService(context)
        loadPets()
        //printPets()
    }

    fun loadPets() {
        viewModelScope.launch {
            val pets = petsRepository.list()
            _petsLiveData.value = pets
        }
    }

    fun printPets() {
        _petsLiveData.value?.forEach { pet ->
            println("Nombre: ${pet.name}, URI: ${pet.image}")
        }
    }

    fun filterPets(name: String?, shortBy: String?) {
        viewModelScope.launch {
            val filterPets = petsRepository.filterByNameAndSort(name, shortBy)
            _petsLiveData.value = filterPets
        }
    }



}