package com.utico.fooddelivery.model

data class profile(val avatar: String, val email: String, val first_name: String, val id: Int, val last_name: String)
data class EditProfileResponse(val name:String, val email: String, val countryCode:String)