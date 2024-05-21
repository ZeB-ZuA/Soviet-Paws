package com.udistrital.soviet_paws.network

import com.udistrital.soviet_paws.models.Pet
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface PetsApi {

    @GET("pets")
    suspend fun getAll(): List<Pet>
    @GET("pet")
    suspend fun get(): Pet

    @Multipart
    @POST("pets")
    suspend fun saveWithImage(
        @Part image: MultipartBody.Part,
        @Part("type") type: RequestBody,
        @Part("name") name: RequestBody,
        @Part("age") age: RequestBody,
        @Part("breed") breed: RequestBody
    ): Pet
    @GET("/pets/filter")
   suspend fun filterByNameAndSort(@Query("name") name: String?, @Query("sortBy") sortBy: String?): List<Pet>
}