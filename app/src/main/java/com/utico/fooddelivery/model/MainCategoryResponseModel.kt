package com.utico.fooddelivery.model

data class MainCategoryResponseModel(
    val MainCategoryData: List<MainCategoryData>,
    val message: String,
    val status: Boolean,
    val statusCode: Int
)
data class MainCategoryData(
    val mainCategoryId: String,
    val mainCategoryName: String,
    var isSelected:Boolean = false

)