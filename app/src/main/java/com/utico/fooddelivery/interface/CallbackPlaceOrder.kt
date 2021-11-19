package com.utico.fooddelivery.`interface`

import com.utico.fooddelivery.model.CartData
import java.text.FieldPosition

interface CallbackPlaceOrder {
    fun passAddToCartDetails(itemMainCategoryName:String,itemSubCategoryName:String,itemName:String,itemId:String,itemQuantity:String,itemPrice:String)
    fun passPlaceOrderList(cartData: MutableList<CartData>,position:Int)
}