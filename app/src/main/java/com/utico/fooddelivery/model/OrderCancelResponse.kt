package com.utico.fooddelivery.model

data class OrderCancelResponse(
    val message: String,
    val status: Boolean,
    val statusCode: Int
)