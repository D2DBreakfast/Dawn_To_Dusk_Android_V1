package com.utico.fooddelivery.model

data class FoodShortDescList(val data:List<FoodShortDesc>)
data class FoodShortDesc(val id:Int?,val email:String?,val first_name:String?,val avatar:String?)

data class FoodSubCategoryList(val data: List<FoodSubCategory>)
data class FoodSubCategory(val first_name : String?)



