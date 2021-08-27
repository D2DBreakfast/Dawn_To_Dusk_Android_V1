package com.utico.fooddelivery.viewmodel

import android.view.View
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utico.fooddelivery.`interface`.HomeInterface
import com.utico.fooddelivery.model.FoodCategory
import com.utico.fooddelivery.model.FoodShortDesc
import com.utico.fooddelivery.repositories.FoodCategoryNameRepository
import com.utico.fooddelivery.repositories.FoodShortDescRepository
import com.utico.fooddelivery.retrofit.ApiService
import com.utico.fooddelivery.retrofit.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel: ViewModel() {
    lateinit var foodShortDescListData : MutableLiveData<FoodShortDesc>


    init {
        foodShortDescListData = MutableLiveData()
    }


    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }

    fun getFoodListShortDescObserver() : MutableLiveData<FoodShortDesc>{
      return foodShortDescListData
    }

    fun makeApiCall(){
        val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
        val response = retroInstance.getAllFoodShortDesc()
    }

    val text: LiveData<String> = _text


   /* fun getAllFoodShortDesc(){
        val response = repository.getAllFoodShortDesc()
        response.enqueue(object : Callback<List<FoodShortDesc>>{
            override fun onResponse(call: Call<List<FoodShortDesc>>, response: Response<List<FoodShortDesc>>) {
                if (response.isSuccessful){
                    _foodShortDescList.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<FoodShortDesc>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })
    }*/
}

