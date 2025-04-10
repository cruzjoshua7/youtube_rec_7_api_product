package com.verycool.storeapi.data.repository

import com.verycool.storeapi.data.api.ApiService
import com.verycool.storeapi.data.model.ClothesDetailsItemModel
import retrofit2.Response
import javax.inject.Inject

class ClothesRepoImpl @Inject constructor(
    private val apiService: ApiService
) : ClothesRepo {
    override suspend fun getClothesList(): Response<List<ClothesDetailsItemModel>> {
        return apiService.getClothesList()
    }
}