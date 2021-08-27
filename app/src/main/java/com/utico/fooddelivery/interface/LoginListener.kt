package com.utico.fooddelivery.`interface`

import androidx.lifecycle.LiveData

interface LoginListener {
    fun logIn(loginResponse: LiveData<String>)
    fun regiStration()
}