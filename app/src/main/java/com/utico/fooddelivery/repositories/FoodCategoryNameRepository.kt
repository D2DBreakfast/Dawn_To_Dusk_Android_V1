package com.utico.fooddelivery.repositories

import com.utico.fooddelivery.retrofit.ApiService

class FoodCategoryNameRepository constructor(val api: ApiService) {
    fun getAllFoodCategoryName() = api.getAllFoodCategoryName()
}