package com.utico.fooddelivery.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.utico.fooddelivery.repositories.FoodShortDescRepository
import com.utico.fooddelivery.viewmodel.HomeViewModel
import com.utico.fooddelivery.viewmodel.ProfileViewModel
import java.lang.IllegalArgumentException

class FoodShortDescFactory constructor(private val repository: FoodShortDescRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            HomeViewModel() as T
        }else{
            throw IllegalArgumentException("ViewModel is Not Fpound")
        }

    }
}
