package com.utico.fooddelivery.`interface`

import androidx.lifecycle.LiveData

interface OTPVerificationListener {
    fun otp(otpResponse: LiveData<String>)
}