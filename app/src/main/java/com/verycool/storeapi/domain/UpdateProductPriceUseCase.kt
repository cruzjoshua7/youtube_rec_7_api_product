package com.verycool.storeapi.domain

import com.verycool.storeapi.data.model.ClothesDetailsItemModel
import com.verycool.storeapi.data.repository.ClothesRepo
import javax.inject.Inject

class UpdateProductPriceUseCase @Inject constructor(
    private val repository: ClothesRepo
) {
    suspend operator fun invoke(product : ClothesDetailsItemModel) : UiState<ClothesDetailsItemModel>{
        return try{
            val response = repository.updateProductPrice(product)
            if(response.isSuccessful){
                response.body()?.let {
                    UiState.SUCCESS(it)
                } ?: UiState.ERROR(Exception("Empty response"))
            } else {
                UiState.ERROR(Exception("Failed with code: ${response.code()}"))
            }
        }catch (e:Exception){
            UiState.ERROR(e)
        }
    }
}