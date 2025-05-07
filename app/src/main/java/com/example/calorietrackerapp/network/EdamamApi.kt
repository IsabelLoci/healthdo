package com.example.calorietrackerapp.network

import com.example.calorietrackerapp.model.EdamamResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EdamamApi {
    @GET("api/food-database/v2/parser")
    suspend fun searchFood(
        @Query("app_id") appId: String,
        @Query("app_key") appKey: String,
        @Query("ingr") ingr: String
    ): Response<EdamamResponse>
}
