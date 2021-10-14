package com.utico.fooddelivery.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utico.fooddelivery.model.*
import com.utico.fooddelivery.retrofit.ApiService
import com.utico.fooddelivery.retrofit.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class HomeViewModel: ViewModel() {

    lateinit var recylerMenuDetailsData:MutableLiveData<MenuDetailsResponseModel>
    lateinit var recyclerSubCategoryData:MutableLiveData<SubCategoryResponseModel>
    var foodCategoryName:String? = null
    var mainCategoryId:String? = "1"

    init {
      recylerMenuDetailsData = MutableLiveData()
      recyclerSubCategoryData = MutableLiveData()
    }



    fun getMenuDetailObservable() : MutableLiveData<MenuDetailsResponseModel>{
      return recylerMenuDetailsData
    }

    fun ApiCallMenuDetails(itemMainCategoryName:String,itemSubCategoryName:String,itemFoodType:String){
      val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
      val call = retroInstance.getMenuDetailsList(itemMainCategoryName,itemSubCategoryName,itemFoodType)
          call.enqueue(object : Callback<MenuDetailsResponseModel>{
              override fun onResponse(
                  call: Call<MenuDetailsResponseModel>, response: Response<MenuDetailsResponseModel>) {
                  if (response.isSuccessful){
                      recylerMenuDetailsData.postValue(response.body())
                  }
              }

              override fun onFailure(call: Call<MenuDetailsResponseModel>, t: Throwable) {
                  recylerMenuDetailsData.postValue(null)
              }

          })
    }



    /*Get the food sub Category list function*/
     fun getSubCategoryObservable() : MutableLiveData<SubCategoryResponseModel> {
         return recyclerSubCategoryData
     }
    /* Make API Call for getting the food subcategory List of data*/
    fun APICallSubCategory(){
       val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
       val call = retroInstance.getSubCategoryList("1")
           call.enqueue(object : Callback<SubCategoryResponseModel>{
               override fun onResponse(call: Call<SubCategoryResponseModel>, response: Response<SubCategoryResponseModel>) {
                   if (response.isSuccessful){
                       recyclerSubCategoryData.postValue(response.body())
                   }
               }

               override fun onFailure(call: Call<SubCategoryResponseModel>, t: Throwable) {
                   recyclerSubCategoryData.postValue(null)
               }

           })
    }




   /* *//*Make Api call for the Food Sub Category Wise Data*//*
    fun getFoodCategoryName(foodCategoryname:String){
        foodCategoryName = foodCategoryname
    }

    fun getFoodSubCategoryWiseObservable() : MutableLiveData<MenuResponse>{
        return recylerFoodShortDescData
    }

    fun MakeApiCallFoodSubCategoryWiseData(){

        val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
        val call = retroInstance.getFoodSubCategoryWiseList(foodCategoryName!!)
            *//*call.enqueue(object : Callback<MenuResponse>{
                override fun onResponse(call: Call<MenuResponse>, response: Response<MenuResponse>) {
                    if (response.isSuccessful) {
                        recylerFoodShortDescData.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<MenuResponse>, t: Throwable) {
                    recylerFoodShortDescData.postValue(null)
                }

            })*//*
    }

    *//*Food Search*//*
    fun getFoodSearchObservable() : MutableLiveData<MenuResponse>{
        return recylerFoodShortDescData
    }
*/


    fun APICallSearchFood(searchText:String){
        val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
        val call = retroInstance.getHomeFoodSearchList(searchText)
            /*call.enqueue(object : Callback<MenuResponse>{
                override fun onResponse(call: Call<MenuResponse>, response: Response<MenuResponse>) {
                 if (response.isSuccessful) {
                    recylerFoodShortDescData.postValue(response.body())
                  }
                }

                override fun onFailure(call: Call<MenuResponse>, t: Throwable) {
                    recylerFoodShortDescData.postValue(null)

                }

            })*/
    }


}

