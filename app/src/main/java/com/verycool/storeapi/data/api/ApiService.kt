package com.verycool.storeapi.data.api

import com.verycool.storeapi.data.model.ClothesDetailsItemModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @GET(ENDPOINT)
    suspend fun getClothesList() : Response<List<ClothesDetailsItemModel>>

    @PUT("$ENDPOINT/{id}")
    suspend fun updateProduct(
        @Path("id") id:Int,
        @Body updatedProduct : ClothesDetailsItemModel
    ) : Response<ClothesDetailsItemModel>
}

