package com.utico.fooddelivery.model

data class SubCategoryMenuDetailsModel(
    val message: String,
    val status: Boolean,
    val statusCode: Int,
    val subCategoryMenuData: SubCategoryMenuData
)
data class SubCategoryMenuData(
    val `data`: List<DataX>
)