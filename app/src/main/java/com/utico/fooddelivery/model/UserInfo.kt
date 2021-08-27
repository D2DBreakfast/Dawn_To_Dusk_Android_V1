package com.utico.fooddelivery.model

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("name") val name:String,
    @SerializedName("email") val email:String,
    @SerializedName("phone") val  phone:String
)
