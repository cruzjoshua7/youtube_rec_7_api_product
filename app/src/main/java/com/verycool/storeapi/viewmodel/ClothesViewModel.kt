package com.verycool.storeapi.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.verycool.storeapi.data.model.ClothesDetailsItemModel
import com.verycool.storeapi.domain.GetClothesUseCase
import com.verycool.storeapi.domain.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClothesViewModel @Inject constructor(
    private val getClothesUseCase: GetClothesUseCase
) : ViewModel(){
    private val _clothesState = MutableStateFlow<UiState<List<ClothesDetailsItemModel>>>(UiState.LOADING)
    val clothesState = _clothesState.asStateFlow()

    init {
        getClothesList()
    }

    fun getClothesList(){
        viewModelScope.launch(Dispatchers.IO){
            getClothesUseCase().collect{
                _clothesState.value = it
            }
        }
    }

    fun updatePrice(
        id:Int,
        newPrice:String
    ){
//        getClothesUseCase.UpdatePrice
        Log.d("ClothesViewModel", "Updated ID $id with new price $newPrice")
    }
}