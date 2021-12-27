package com.utico.fooddelivery.model

data class SubscriptionUpcomingMealResponseModel(
    val message: String,
    val status: Boolean,
    val statusCode: Int,
    val upcomingMeals: List<upcomingMeals>
)
data class upcomingMeals(
    val day: String,
    val description: String,
    val image: String,
    val meal: String,
    val subscriptionId: String,
    val subscriptionType: String
)