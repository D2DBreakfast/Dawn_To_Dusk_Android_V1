package com.utico.fooddelivery.model

data class DeleteCartItemResponse(
    val message: String,
    val status: Boolean,
    val statusCode: Int
)