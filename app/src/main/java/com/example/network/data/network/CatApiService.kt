package com.example.network.data.network

import com.example.network.data.model.CatImageModel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

// NEVER DO IT IN PROD
private const val API_KEY = "live_YdQaHsreqr4whhZGKKOCe27CqYnmZL42zyPH96bUl3ujq8lkGHb2O5FRzVJFYTbS"

interface CatApiService {
    @GET("v1/images/search")
    suspend fun getRandomCats(
        @Query("limit") limit: Int = 10,
        @Header("x-api-key") apiKey: String = "API_KEY"
    ): List<CatImageModel>
}
