package com.udistrital.soviet_paws.network

import com.udistrital.soviet_paws.models.Pet
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface PetsApi {

    @GET("pets")
    suspend fun getAll(): List<Pet>

    @Multipart
    @POST("pets")
    suspend fun saveWithImage(
        @Part image: MultipartBody.Part,
        @Part("type") type: RequestBody,
        @Part("name") name: RequestBody,
        @Part("age") age: RequestBody,
        @Part("breed") breed: RequestBody
    ): Pet
    @GET("filterPets")
   suspend fun filterByNameAndSort(name: String?, sortBy: String?): List<Pet>
}