package com.verycool.storeapi.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.verycool.storeapi.data.model.ClothesDetailsItemModel
import com.verycool.storeapi.domain.GetClothesUseCase
import com.verycool.storeapi.domain.UiState
import com.verycool.storeapi.domain.UpdateProductPriceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClothesViewModel @Inject constructor(
    private val getClothesUseCase: GetClothesUseCase,
    private val updateProductPriceUseCase: UpdateProductPriceUseCase
) : ViewModel(){

    private val _clothesState = MutableStateFlow<UiState<List<ClothesDetailsItemModel>>>(UiState.LOADING)
    val clothesState = _clothesState.asStateFlow()


    fun getClothesList(){
        viewModelScope.launch(Dispatchers.IO){
            getClothesUseCase().collect{
                _clothesState.value = it
            }
        }
    }

    fun updatePrice(id: Int, newPrice: String) {
        val currentList = (_clothesState.value as? UiState.SUCCESS)?.response ?: return
        val product = currentList.find { it.id == id } ?: return

        val updatedProduct = product.copy(price = newPrice.toDoubleOrNull() ?: product.price)

        viewModelScope.launch {
            when (val result = updateProductPriceUseCase(updatedProduct)) {
                is UiState.SUCCESS -> {
                    Log.i("ClothesViewModel", "Successfully updated product: ${result.response}")

                    val updatedList = currentList.map {
                        if (it.id == id) result.response else it
                    }
                    _clothesState.value = UiState.SUCCESS(updatedList)
                }
                is UiState.ERROR -> {
                    Log.i("ClothesViewModel", "Update failed: ${result.e.message}")
                }
                else -> Unit
            }
        }
    }
}