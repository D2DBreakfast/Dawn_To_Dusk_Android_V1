package com.utico.fooddelivery.`interface`
interface CallbackOrderHistory {
    fun orderCancelDialog()
    fun placedOrderCancel(userId:String,orderId:String)
    fun orderCancelReason(reason:String)
}