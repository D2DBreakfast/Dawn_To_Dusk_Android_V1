package com.utico.fooddelivery.model

data class SubCategoryResponseModel(
    val SubCategoryData: List<SubCategoryData>,
    val message: String,
    val status: Boolean,
    val statusCode: Int
)

data class SubCategoryData(
    val mainCategoryId: String,
    val subCategoryId: String,
    val subCategoryName: String
)