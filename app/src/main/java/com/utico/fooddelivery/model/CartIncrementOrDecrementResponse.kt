package com.utico.fooddelivery.model

data class CartIncrementOrDecrementResponse(
    val message: String,
    val status: Boolean,
    val statusCode: Int
)