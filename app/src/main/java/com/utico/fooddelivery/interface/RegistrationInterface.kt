package com.utico.fooddelivery.`interface`

import androidx.lifecycle.LiveData

interface RegistrationInterface {
    fun registration(registerResponse: LiveData<String>)
}