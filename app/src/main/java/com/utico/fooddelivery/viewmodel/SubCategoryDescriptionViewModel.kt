package com.utico.fooddelivery.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utico.fooddelivery.`interface`.CallbackSubCategoryDescription
import com.utico.fooddelivery.model.*
import com.utico.fooddelivery.retrofit.ApiService
import com.utico.fooddelivery.retrofit.RetroInstance
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SubCategoryDescriptionViewModel : ViewModel() {
   private lateinit var subCategoryRelatedResponse:MutableLiveData<MenuDetailsResponseDataModel>
   private lateinit var vegMenuResponse:MutableLiveData<MenuDetailsResponseDataModel>
   private lateinit var addToCartResponse:MutableLiveData<CartIncrementOrDecrementResponse>
   private lateinit var addOnsResponse:MutableLiveData<AddOnsDataModel>
   private lateinit var errorResponse:MutableLiveData<String>


    init {
        subCategoryRelatedResponse = MutableLiveData()
        vegMenuResponse = MutableLiveData()
        addToCartResponse = MutableLiveData()
        addOnsResponse = MutableLiveData()
        errorResponse = MutableLiveData()
   }


    fun subCategoryRelatedMenuDetailsObservable():MutableLiveData<MenuDetailsResponseDataModel>{
        return subCategoryRelatedResponse
    }

    fun apiCallToGetSubCategoryRelatedMenuDetails(mainCategoryId:String,subCategoryId:String){
        val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
        val call = retroInstance.getSubCategoryRelatedDetails(mainCategoryId,subCategoryId)
            call.enqueue(object : Callback<MenuDetailsResponseDataModel>{
                override fun onResponse(call: Call<MenuDetailsResponseDataModel>, response: Response<MenuDetailsResponseDataModel>) {
                  if (response.isSuccessful){
                      subCategoryRelatedResponse.postValue(response.body())
                  }
                }

                override fun onFailure(call: Call<MenuDetailsResponseDataModel>, t: Throwable) {
                  // subCategoryRelatedResponse.postValue(null)
                    errorResponse.postValue(t.message)
                }

            })
    }/*Fetch subCategory Menu Details*/


    fun getVegMenuObservable():MutableLiveData<MenuDetailsResponseDataModel>{
        return  vegMenuResponse
    }


    fun apiCallVegMenu(mainCategoryId:String,subCategoryId:String){
        val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
        val call = retroInstance.getVegMenuDetails(mainCategoryId,subCategoryId)
            call.enqueue(object : Callback<MenuDetailsResponseDataModel>{
                override fun onResponse(call: Call<MenuDetailsResponseDataModel>, response: Response<MenuDetailsResponseDataModel>) {
                    if (response.isSuccessful){
                        vegMenuResponse.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<MenuDetailsResponseDataModel>, t: Throwable) {
                    vegMenuResponse.postValue(null)
                }

            })
    }




    fun addToCartObservable() : MutableLiveData<CartIncrementOrDecrementResponse>{
        return addToCartResponse
    }


    fun apiCallAddToCart(itemMainCategoryName:String,itemSubCategoryName:String,itemFoodType:Boolean,itemName:String,itemId:String,itemQuantity:String,itemPrice:String,itemImageUrl:String,itemDescription:String,userId:String,itemBasePrice:String) {
        val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
        val call = retroInstance.addToCart(itemMainCategoryName,itemSubCategoryName,itemFoodType,itemName,itemId,itemQuantity,itemPrice,itemImageUrl,itemDescription,userId,itemBasePrice)
        call.enqueue(object : retrofit2.Callback<CartIncrementOrDecrementResponse>{
            override fun onResponse(call: Call<CartIncrementOrDecrementResponse>, response: Response<CartIncrementOrDecrementResponse>) {
                if (response.isSuccessful){
                    addToCartResponse.postValue(response.body())
                }
            }
            override fun onFailure(call: Call<CartIncrementOrDecrementResponse>, t: Throwable) {
                addToCartResponse.postValue(null)

            }
        })

    }

    /*get Addons Data*/

    fun addOnsObservable():MutableLiveData<AddOnsDataModel>{
        return addOnsResponse
    }

    fun apiCallAddons(itemId:String){
     val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
     val call = retroInstance.getAddOns(itemId)
         call.enqueue(object :Callback<AddOnsDataModel>{
             override fun onResponse(call: Call<AddOnsDataModel>, response: Response<AddOnsDataModel>) {
                 if (response.isSuccessful){
                     addOnsResponse.postValue(response.body())
                 }
             }

             override fun onFailure(call: Call<AddOnsDataModel>, t: Throwable) {
                 addOnsResponse.postValue(null)
             }
         })
    }

}