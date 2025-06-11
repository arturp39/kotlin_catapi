package com.example.network.data.repository

import com.example.network.data.model.CatImageModel
import com.example.network.data.network.CatApiService

class CatRepository(
    private val apiService: CatApiService
) {
    suspend fun getRandomCats(limit: Int = 10): Result<List<CatImageModel>> {
        return try {
            val cats = apiService.getRandomCats(limit)
            Result.success(cats)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}