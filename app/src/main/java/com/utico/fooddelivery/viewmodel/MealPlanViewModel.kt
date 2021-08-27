package com.utico.fooddelivery.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.utico.fooddelivery.model.MealPlanList
import com.utico.fooddelivery.retrofit.ApiService
import com.utico.fooddelivery.retrofit.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealPlanViewModel : ViewModel() {

    lateinit var recyclerMealsPlanData:MutableLiveData<MealPlanList>


    init {
        recyclerMealsPlanData = MutableLiveData()
    }


    fun getMealPlanListObserable(): MutableLiveData<MealPlanList>{
        return recyclerMealsPlanData
    }

    fun makeApiCallGetMealPlan() {
        val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
        val call = retroInstance.getAllMealPlanList()
        call.enqueue(object : Callback<MealPlanList>{
            override fun onResponse(call: Call<MealPlanList>, response: Response<MealPlanList>) {
               if (response.isSuccessful) {
                   recyclerMealsPlanData.postValue(response.body())
               }
            }

            override fun onFailure(call: Call<MealPlanList>, t: Throwable) {
                recyclerMealsPlanData.postValue(null)
            }

        })
    }

}