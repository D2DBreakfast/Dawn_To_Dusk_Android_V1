package com.utico.fooddelivery.model

data class AddToCartResponse(
    val message: String,
    val status: Boolean,
    val statusCode: Int
)