package com.utico.fooddelivery.model

data class OrderCancelReasonResponseModel(
    val cancelReasons: List<CancelReason>,
    val message: String,
    val status: Boolean,
    val statusCode: Int
)

data class CancelReason(
    val reason: String
)