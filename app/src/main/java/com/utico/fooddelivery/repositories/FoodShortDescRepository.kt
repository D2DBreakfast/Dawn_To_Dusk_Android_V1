package com.utico.fooddelivery.repositories

import com.utico.fooddelivery.retrofit.ApiService
import com.utico.fooddelivery.retrofit.RetroInstance

class FoodShortDescRepository constructor(private val retroInstance: ApiService = RetroInstance.getRetroInstance().create(ApiService::class.java)){
    fun getAllFoodShortDesc() = retroInstance.getAllFoodShortDesc()
}