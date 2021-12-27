package com.utico.fooddelivery.`interface`

import java.time.temporal.TemporalAmount

interface CallbackSubCategoryDescription {
    fun addToCartData(itemName:String,itemDescription:String,itemPrice:String,itemMainCategoryName:String,
                      itemSubCategoryName:String,itemFoodType:Boolean,itemQuantity:String,itemId:String,itemImageUrl:String)

    fun addOnsButtonClickEvent(itemName:String,itemDescription:String,itemPrice:String,itemMainCategoryName:String,
                               itemSubCategoryName:String,itemFoodType:Boolean,itemQuantity:String,itemId:String,itemImageUrl:String)
    fun getSelectedAddOnsValue(amount:String)
}
