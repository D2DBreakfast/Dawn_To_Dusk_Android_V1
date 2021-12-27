package com.utico.fooddelivery.model

data class SubscriptionTypesDataResponseModel(
    val message: String,
    val status: Boolean,
    val statusCode: Int,
    val subscriptionTypes: List<subscriptionTypes>
)
data class subscriptionTypes(
    val subscriptionDescription: String,
    val subscriptionId: String,
    val subscriptionImage: String,
    val subscriptionLeastAmount: String,
    val subscriptionType: String
)
