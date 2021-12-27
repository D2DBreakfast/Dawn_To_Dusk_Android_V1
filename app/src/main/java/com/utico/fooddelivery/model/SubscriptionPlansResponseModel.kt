package com.utico.fooddelivery.model

data class SubscriptionPlansResponseModel(
    val message: String,
    val status: Boolean,
    val statusCode: Int,
    val subscriptionPlans: List<SubscriptionPlan>
)