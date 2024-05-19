package com.udistrital.soviet_paws.viewModels

import PetService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udistrital.soviet_paws.models.Pet
import kotlinx.coroutines.launch

class PetsListViewModel: ViewModel() {
    private val petsRepository = PetService()
    private val _petsLiveData = MutableLiveData<List<Pet>>()
    val petsLiveData: LiveData<List<Pet>> = _petsLiveData

    init {
        loadPets()
    }

    fun loadPets() {
        viewModelScope.launch {
            val films = petsRepository.list()
            _petsLiveData.value = films
        }
    }

}