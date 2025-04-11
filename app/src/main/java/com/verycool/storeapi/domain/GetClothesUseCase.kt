package com.verycool.storeapi.domain

import com.verycool.storeapi.data.model.ClothesDetailsItemModel
import com.verycool.storeapi.data.repository.ClothesRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetClothesUseCase @Inject constructor(
    private val repository: ClothesRepo
) {
    operator fun invoke(): Flow<UiState<List<ClothesDetailsItemModel>>> = flow {
        emit(UiState.LOADING)

        val response = repository.getClothesList()
        if (response.isSuccessful) {
            response.body()?.let { clothesList ->
                emit(UiState.SUCCESS(clothesList))
            } ?: throw Exception("No clothes found")
        }
    }.catch { e->
        if(e is Exception){
            emit(UiState.ERROR(e))
        }
    }
}