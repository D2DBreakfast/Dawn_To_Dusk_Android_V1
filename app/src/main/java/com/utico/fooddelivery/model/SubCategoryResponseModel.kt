package com.utico.fooddelivery.model

data class SubCategoryResponseModel(
    val message: String,
    val status: Boolean,
    val statusCode: Int,
    val subCategoryData: List<SubCategoryData>
)

data class SubCategoryData(
    val _id: String,
    val mainCategoryId: String,
    val subCategoryId: String,
    val subCategoryName: String
)