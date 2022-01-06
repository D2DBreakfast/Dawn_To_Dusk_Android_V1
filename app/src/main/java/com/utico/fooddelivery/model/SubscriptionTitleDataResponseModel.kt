package com.utico.fooddelivery.model

data class SubscriptionTitleDataResponseModel(
    val message: String,
    val status: Boolean,
    val statusCode: Int,
        val SubscriptionTitle: List<SubscriptionTitle>
)
data class SubscriptionTitle(
    val subscriptionDescription: String,
    val subscriptionId: String,
    val subscriptionImage: String,
    val subscriptionLeastAmount: String,
    val subscriptionTitle: String
)
