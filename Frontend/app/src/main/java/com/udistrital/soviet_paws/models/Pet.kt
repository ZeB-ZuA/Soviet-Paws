package com.udistrital.soviet_paws.models

import android.net.Uri

data class Pet(
    val id: Int? =  null,
    val name: String,
    val type: String,
    val age: Int,
    val breed: String,
    val imageUri: Uri?
)
