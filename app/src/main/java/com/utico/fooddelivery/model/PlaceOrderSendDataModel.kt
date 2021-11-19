package com.utico.fooddelivery.model

import com.google.gson.annotations.SerializedName

data class PlaceOrderSendDataModel(
    @SerializedName("address") var address: String?,
    var placeOrderArray: List<PlaceOrderArray>,
    @SerializedName("sector") var sector: String?,
    @SerializedName("totalAmount") var totalAmount: String?,
    @SerializedName("userId") var userId: String?
)
data class PlaceOrderArray(
    @SerializedName("cartId") var cartId: String,
    @SerializedName("itemFoodType") var itemFoodType: String,
    @SerializedName("itemId") var itemId: String,
    @SerializedName("itemMainCategoryName") var itemMainCategoryName: String,
    @SerializedName("itemName") var itemName: String,
    @SerializedName("itemPrice") var itemPrice: String,
    @SerializedName("itemQuantity") var itemQuantity: String,
    @SerializedName("itemSubCategoryName") var itemSubCategoryName: String,
    @SerializedName("orderStatus") var orderStatus: String
)