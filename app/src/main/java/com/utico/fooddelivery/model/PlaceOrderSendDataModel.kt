package com.utico.fooddelivery.model

import com.google.gson.annotations.SerializedName

data class PlaceOrderSendDataModel(
    @SerializedName("villa") var villa: String?,
    @SerializedName("landMark") var landMark: String?,
    var itemArray: List<itemArray>,
    @SerializedName("sectorId") var sector: String?,
    @SerializedName("totalAmount") var totalAmount: String?,
    @SerializedName("userId") var userId: String?,
    @SerializedName("categoryType") var categoryType:String)

data class itemArray(
    @SerializedName("cartId") var cartId: String,
    @SerializedName("itemFoodType") var itemFoodType: Boolean,
    @SerializedName("itemId") var itemId: String,
    @SerializedName("itemMainCategoryName") var itemMainCategoryName: String,
    @SerializedName("itemName") var itemName: String,
    @SerializedName("itemPrice") var itemPrice: String,
    @SerializedName("itemBaseQuantity") var itemBaseQuantity: String,
    @SerializedName("itemSubCategoryName") var itemSubCategoryName: String)

