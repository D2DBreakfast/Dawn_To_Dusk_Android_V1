package com.utico.fooddelivery.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utico.fooddelivery.model.FoodList
import com.utico.fooddelivery.retrofit.ApiService
import com.utico.fooddelivery.retrofit.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {

    lateinit var recylerFoodSearListData:MutableLiveData<FoodList>

    init {
        recylerFoodSearListData = MutableLiveData()
    }

    fun getFoodSearchListObserable() : MutableLiveData<FoodList>{
        return recylerFoodSearListData
    }

    fun getFoodSearchList(){
        val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
        val call = retroInstance.searchFoodList()
        call.enqueue(object : Callback<FoodList>{
            override fun onResponse(call: Call<FoodList>, response: Response<FoodList>) {
                if(response.isSuccessful) {
                    recylerFoodSearListData.postValue(response.body())
                }

            }

            override fun onFailure(call: Call<FoodList>, t: Throwable) {
                recylerFoodSearListData.postValue(null)

            }

        })
    }
}