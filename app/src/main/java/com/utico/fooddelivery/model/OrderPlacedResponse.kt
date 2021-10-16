package com.utico.fooddelivery.model

data class OrderPlacedResponse(
    val message: String,
    val status: Boolean,
    val statusCode: Int
)