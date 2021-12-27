package com.utico.fooddelivery.`interface`

import com.utico.fooddelivery.model.ViewCartData

interface CallbackViewCart {
   // fun passAddToCartDetails(itemMainCategoryName:String,itemSubCategoryName:String,itemName:String,itemId:String,itemBaseQuantity:String,itemPrice:String)
    fun passPlaceOrderList(cartData: MutableList<ViewCartData>)
    fun deleteItem(userId:String,cartId:String)
    fun decrementOrIncremenItem(userId:String,cartId:String,itemQuantity:String,itemPrice:String)
}