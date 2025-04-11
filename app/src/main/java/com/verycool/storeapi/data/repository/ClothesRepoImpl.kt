package com.verycool.storeapi.data.repository

import android.util.Log
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

    override suspend fun updateProductPrice(product: ClothesDetailsItemModel): Response<ClothesDetailsItemModel> {
        val safeProduct = product.copy(
            id = product.id ?: 0,
            title = product.title ?: "",
            price = product.price ?: 0.0,
            description = product.description ?: "",
            category = product.category ?: "",
            image = product.image ?: ""
        )
        val response = apiService.updateProduct(safeProduct.id!!, safeProduct)

        return response
    }
// why do i have to use !! here

}