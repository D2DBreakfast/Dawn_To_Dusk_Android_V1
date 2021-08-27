package com.utico.fooddelivery.model

data class MealPlanList(val data:List<MealPlan>)
data class MealPlan(val id:Int?,val email:String?,val first_name:String?,val avatar:String?)
