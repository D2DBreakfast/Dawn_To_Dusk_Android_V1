package com.utico.fooddelivery.model

data class MenuDetailsResponseModel(
    val menuData: MenuData,
    val message: String,
    val status: Boolean,
    val statusCode: Int
)
data class MenuData(
    val `data`: List<DataX>)

data class DataX(
    val _id: String,
    val itemDescription: String,
    val itemFoodType: String,
    val itemId: String,
    val itemImageUrl: String,
    val itemMainCategoryName: String,
    val itemName: String,
    val itemPrice: String,
    val itemQuantity: String,
    val itemSubCategoryName: String
)