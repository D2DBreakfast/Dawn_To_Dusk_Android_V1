package com.utico.fooddelivery.viewmodel

import android.content.ClipData
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utico.fooddelivery.`interface`.HomeInterface
import com.utico.fooddelivery.model.FoodCategory
import com.utico.fooddelivery.model.FoodShortDesc
import com.utico.fooddelivery.model.FoodShortDescList
import com.utico.fooddelivery.repositories.FoodCategoryNameRepository
import com.utico.fooddelivery.repositories.FoodShortDescRepository
import com.utico.fooddelivery.retrofit.ApiService
import com.utico.fooddelivery.retrofit.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel: ViewModel() {

    lateinit var recylerFoodShortDescData:MutableLiveData<FoodShortDescList>

    init {
      recylerFoodShortDescData = MutableLiveData()
    }


    fun getyFoodShortDescListObserable() : MutableLiveData<FoodShortDescList>{
      return recylerFoodShortDescData
    }

    fun makeApiCallFoodDesc(){
      val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
      val call = retroInstance.getAllFoodShortDescDetailsList()
          call.enqueue(object : Callback<FoodShortDescList>{
              override fun onResponse(
                  call: Call<FoodShortDescList>, response: Response<FoodShortDescList>) {
                  if (response.isSuccessful){
                      recylerFoodShortDescData.postValue(response.body())
                  }
              }

              override fun onFailure(call: Call<FoodShortDescList>, t: Throwable) {
                  recylerFoodShortDescData.postValue(null)
              }

          })
    }

}

