package com.utico.fooddelivery.model

data class ViewCartResponseModel(
    val ViewCartData: List<ViewCartData>,
    val deliveryCharge: String,
    val itemCount: String,
    val itemVat: String,
    val message: String,
    val priceAfterVat: String,
    val priceBeforeVat: String,
    val status: Boolean,
    val statusCode: Int
)

data class ViewCartData(
    val cartDate: String,
    val cartId: String,
    val itemBasePrice: String,
    var itemBaseQuantity:String,
    val itemDescription: String,
    val itemFoodType: Boolean,
    val itemId: String,
    val itemImageUrl: String,
    val itemMainCategoryName: String,
    val itemName: String,
    val itemPrice: String,
    var itemQuantity: String,
    val itemSubCategoryName: String,
    val userId: String,
)