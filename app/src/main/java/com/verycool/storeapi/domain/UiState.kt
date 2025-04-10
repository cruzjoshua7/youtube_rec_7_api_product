package com.verycool.storeapi.domain

import retrofit2.Response

sealed class UiState<out T> {
    object LOADING : UiState<Nothing>()
    data class SUCCESS<T>(val response: T) : UiState<T>()
    data class ERROR(val e : Exception) : UiState<Nothing>()
}