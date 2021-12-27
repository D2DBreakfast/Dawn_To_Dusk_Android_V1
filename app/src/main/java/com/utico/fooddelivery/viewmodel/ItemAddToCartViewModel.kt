package com.utico.fooddelivery.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utico.fooddelivery.model.CartIncrementOrDecrementResponse
import com.utico.fooddelivery.retrofit.ApiService
import com.utico.fooddelivery.retrofit.RetroInstance
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
class ItemAddToCartViewModel : ViewModel() {
    private lateinit var cartResponse:MutableLiveData<CartIncrementOrDecrementResponse>

    init {
     cartResponse = MutableLiveData()
    }

    fun postCartObservable() : MutableLiveData<CartIncrementOrDecrementResponse>{
        return cartResponse
    }


   fun apiCall(itemMainCategoryName:String,itemSubCategoryName:String,itemFoodType:Boolean,itemName:String,itemId:String,itemQuantity:String,itemPrice:String,itemImageUrl:String,itemDescription:String,userId:String,itemBasePrice:String) {
       val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
       val call = retroInstance.addToCart(itemMainCategoryName,itemSubCategoryName,itemFoodType,itemName,itemId,itemQuantity,itemPrice,itemImageUrl,itemDescription,userId,itemBasePrice)
       call.enqueue(object : retrofit2.Callback<CartIncrementOrDecrementResponse>{
           override fun onResponse(call: Call<CartIncrementOrDecrementResponse>, response: Response<CartIncrementOrDecrementResponse>) {
               if (response.isSuccessful){
                   cartResponse.postValue(response.body())
               }
           }
           override fun onFailure(call: Call<CartIncrementOrDecrementResponse>, t: Throwable) {
               cartResponse.postValue(null)

           }
       })

    }
}
