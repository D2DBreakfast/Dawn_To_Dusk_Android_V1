package com.utico.fooddelivery.model

data class UserRegistrationResponseModel(
    val loginData: LoginData,
    val message: String,
    val status: Boolean,
    val statusCode: Int
)

data class LoginData(
    val countryCode: String,
    val email: String,
    val fullName: String,
    val mobileNo: String,
    val mobileOtp: String,
    val registerDate: String,
    val token: String,
    val userId: String
)