package com.utico.fooddelivery.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.utico.fooddelivery.repositories.FoodCategoryNameRepository
import com.utico.fooddelivery.viewmodel.HomeViewModel

/*
class FoodCategoryNameViewModelFactory constructor(private val repository: FoodCategoryNameRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            HomeViewModel(this.repository) as T
        }else{
           throw IllegalAccessException("ViewModel Not Found")
        }
    }
}*/
