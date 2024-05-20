package com.udistrital.soviet_paws.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class Pet @JvmOverloads constructor(
    val name: String = "",
    val type: String = "",
    val age: Int = 0,
    val breed: String = "",
    val imageUri: Uri
):Parcelable
