package com.verycool.storeapi.data.model


import com.google.gson.annotations.SerializedName

data class ClothesDetailsItemModel(
    @SerializedName("category")
    val category: String? = "",
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("image")
    val image: String? = "",
    @SerializedName("price")
    val price: Double? = 0.0,
    @SerializedName("rating")
    val rating: RatingModel? = RatingModel(),
    @SerializedName("title")
    val title: String? = ""
)