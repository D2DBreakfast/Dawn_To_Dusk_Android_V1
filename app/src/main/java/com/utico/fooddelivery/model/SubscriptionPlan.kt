package com.utico.fooddelivery.model

data class SubscriptionPlan(
    val days: String,
    val price: String,
    val subscriptionId: String,
    val subscriptionType: String,
    var isSelected:Boolean = false

)