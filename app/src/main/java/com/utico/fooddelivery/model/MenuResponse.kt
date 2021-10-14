package com.utico.fooddelivery.model

data class MenuResponse(
    val code: Int,
    val `data`: List<DataX>,
    val message: String,
    val status: Boolean
)
