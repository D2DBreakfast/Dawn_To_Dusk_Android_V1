package com.utico.fooddelivery.model

data class MenuListResponses(
    val menuDetails: List<MenuDetail>,
    val message: String,
    val status: Boolean,
    val statusCode: Int
)

data class MenuDetail(
    val itemDetails: List<ItemDetail>,
    val subCategoryName: String
)

data class ItemDetail(
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