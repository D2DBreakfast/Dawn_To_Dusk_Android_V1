package com.utico.fooddelivery.model

data class MenuDetailsResponseDataModel(
    val message: String,
    val status: Boolean,
    val statusCode: Int,
    val subCategoryMenuData: List<SubCategoryMenuData>
)

data class SubCategoryMenuData(
    val addOnIds: List<Any>,
    val itemDescription: String,
    val itemFoodType: Boolean,
    val itemId: String,
    val itemImageUrl: String,
    val itemMainCategoryName: String,
    val itemName: String,
    val itemPrice: String,
    val itemQuantity: String,
    val itemSubCategoryName: String,
    val mainCategoryId: String,
    val subCategoryId: String
)