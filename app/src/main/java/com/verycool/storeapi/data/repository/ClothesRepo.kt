package com.verycool.storeapi.data.repository

import com.verycool.storeapi.data.model.ClothesDetailsItemModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ClothesRepo {

    suspend fun getClothesList(): Response<List<ClothesDetailsItemModel>>

    suspend fun updateProductPrice(product: ClothesDetailsItemModel):Response<ClothesDetailsItemModel>

}