package com.utico.fooddelivery.model

data class AddToCartDetailsResponseModel(
    val cartData: List<CartData>,
    val message: String,
    val status: Boolean,
    val statusCode: Int
)

data class CartData(
    val itemId: String,
    val itemMainCategoryName: String,
    val itemName: String,
    val itemPrice: String,
    val itemQuantity: String,
    val itemSubCategoryName: String,
    val orderDate: String,
    val orderId: String,
    val orderStatus: String,
    val userId: String
)