package com.udistrital.soviet_paws.viewModels

import PetService
import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udistrital.soviet_paws.models.Pet
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

class AddPetsViewModel(private val petService: PetService, private val context: Context) : ViewModel() {

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> get() = _name
    private val _type = MutableLiveData<String>()
    val type: LiveData<String> get() = _type
    private val _age = MutableLiveData<Int>()
    val age: LiveData<Int> get() = _age
    private val _breed = MutableLiveData<String>()
    val breed: LiveData<String> get() = _breed
    private val _imageUri = MutableLiveData<String?>()
    val imageUri: LiveData<String?> get() = _imageUri

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

    fun setImageUri(uri: String?) {
        _imageUri.value = uri
    }

    fun save() {
        viewModelScope.launch {
            val name = _name.value ?: ""
            val type = _type.value ?: ""
            val age = _age.value ?: 0
            val breed = _breed.value ?: ""
            val imageUri = _imageUri.value
            if (imageUri != null) {
                val pet = Pet(

                    name = name,
                    type = type,
                    age = age,
                    breed = breed,
                    image = imageUri
                )
                petService.save(pet)
            } else {
                println("No image selected")
            }
        }
    }
    fun printInfo(){
        println("name: "+_name.value)
        println("type: "+_type.value)
        println("age: "+_age.value)
        println("breed: "+_breed.value)
        println("uri: "+_imageUri.value)

    }

}

