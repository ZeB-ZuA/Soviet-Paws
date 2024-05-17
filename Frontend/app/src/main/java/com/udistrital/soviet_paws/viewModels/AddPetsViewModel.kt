package com.udistrital.soviet_paws.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddPetsViewModel(): ViewModel(){

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> get() = _name
    private val _type = MutableLiveData<String>()
    val type: LiveData<String> get() = _type
    private val _age = MutableLiveData<Int>()
    val age: LiveData<Int> get() = _age
    private val _breed = MutableLiveData<String>()
    val breed: LiveData<String> get() = _breed

    fun setName(name: String) {
        _name.value = name
    }

    fun setType(type: String) {
        _type.value = type
    }

    fun setAge(age: Int) {
        _age.value = age
    }

    fun setBreed(breed: String) {
        _breed.value = breed
    }
}