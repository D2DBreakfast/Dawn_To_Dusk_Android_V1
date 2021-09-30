package com.utico.fooddelivery.`interface`

import androidx.lifecycle.LiveData

interface ProfileListener {
    fun editProfile(editProfileResponse: LiveData<String>)
}