package com.verycool.storeapi.data.api

import com.verycool.storeapi.data.model.ClothesDetails
import com.verycool.storeapi.data.model.ClothesDetailsItemModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(ENDPOINT_LIST)
    suspend fun getClothesList() : Response<List<ClothesDetailsItemModel>>

}

