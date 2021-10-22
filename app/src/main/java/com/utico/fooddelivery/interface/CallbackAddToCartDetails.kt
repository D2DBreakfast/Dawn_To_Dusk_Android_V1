package com.utico.fooddelivery.`interface`

import com.utico.fooddelivery.model.CartData

interface CallbackAddToCartDetails {
    fun passAddToCartDetails(itemMainCategoryName:String,itemSubCategoryName:String,itemName:String,itemId:String,itemQuantity:String,itemPrice:String)
    fun passAddToCartList(cartData: MutableList<CartData>)
}