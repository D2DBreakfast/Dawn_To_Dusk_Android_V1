package com.utico.fooddelivery.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utico.fooddelivery.retrofit.ApiService
import com.utico.fooddelivery.retrofit.RetroInstance
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
class ItemDescriptionViewModel : ViewModel() {
    private lateinit var cartResponse:MutableLiveData<String>

    init {
     cartResponse = MutableLiveData()
    }

    fun postCartObservable() : MutableLiveData<String>{
        return cartResponse
    }


   fun apiCall(itemMainCategoryName:String,itemSubCategoryName:String,itemFoodType:String,itemName:String,itemId:String,itemQuantity:String,itemPrice:String,userId:String) {
       val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
       val call = retroInstance.addToCart(itemMainCategoryName,itemSubCategoryName,itemFoodType,itemName,itemId,itemQuantity,itemPrice,userId)
       call.enqueue(object : retrofit2.Callback<ResponseBody>{
           override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
               if (response.isSuccessful){
                   cartResponse.postValue(response.body()?.toString())
               }
           }
           override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
               cartResponse.postValue(t.message)

           }
       })

    }
}
