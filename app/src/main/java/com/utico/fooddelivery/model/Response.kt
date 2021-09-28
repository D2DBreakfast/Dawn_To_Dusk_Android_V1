package com.utico.fooddelivery.model

data class Response(val CategoryName: String, val Code: Int, val Description: String,
    val FoodName: String,
    val FoodQuantity: String,
    val Price: String,
    val Status: String,
    val SubCategoryName: String,
    val VegrNonVeg: String
)