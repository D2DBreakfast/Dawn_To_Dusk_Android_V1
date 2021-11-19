package com.utico.fooddelivery.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utico.fooddelivery.model.SubCategoryMenuDetailsModel
import com.utico.fooddelivery.retrofit.ApiService
import com.utico.fooddelivery.retrofit.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SubCategoryDescriptionViewModel : ViewModel() {
   private lateinit var subCategoryRelatedResponse:MutableLiveData<SubCategoryMenuDetailsModel>

   init {
       subCategoryRelatedResponse = MutableLiveData()
   }

    fun subCategoryRelatedMenuDetailsObservable():MutableLiveData<SubCategoryMenuDetailsModel>{
        return subCategoryRelatedResponse
    }

    fun apiCallToGetSubCategoryRelatedMenuDetails(mainCategoryId:String,subCategoryId:String){
        val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
        val call = retroInstance.getSubCategoryRelatedDetails(mainCategoryId,subCategoryId)
            call.enqueue(object : Callback<SubCategoryMenuDetailsModel>{
                override fun onResponse(call: Call<SubCategoryMenuDetailsModel>, response: Response<SubCategoryMenuDetailsModel>) {
                  if (response.isSuccessful){
                      subCategoryRelatedResponse.postValue(response.body())
                  }
                }

                override fun onFailure(call: Call<SubCategoryMenuDetailsModel>, t: Throwable) {
                   subCategoryRelatedResponse.postValue(null)
                }

            })

    }
}