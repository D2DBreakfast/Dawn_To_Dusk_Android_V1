package com.utico.fooddelivery.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utico.fooddelivery.model.FoodShortDescList
import com.utico.fooddelivery.model.FoodSubCategoryList
import com.utico.fooddelivery.retrofit.ApiService
import com.utico.fooddelivery.retrofit.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class HomeViewModel: ViewModel() {

    lateinit var recylerFoodShortDescData:MutableLiveData<FoodShortDescList>
    lateinit var recyclerFoodSubCategoryData:MutableLiveData<FoodSubCategoryList>
    var foodCategoryName:String? = null

    init {
      recylerFoodShortDescData = MutableLiveData()
      recyclerFoodSubCategoryData = MutableLiveData()
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


    /*Get the food sub Category list function*/
     fun getFoodSubCategoryObserable() : MutableLiveData<FoodSubCategoryList> {
         return recyclerFoodSubCategoryData
     }

    /* Make API Call for getting the food subcategory List of data*/
    fun APICallFoodSubCategory(){
       val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
       val call = retroInstance.getAllFoodSubCategoryList()
           call.enqueue(object : Callback<FoodSubCategoryList>{
               override fun onResponse(call: Call<FoodSubCategoryList>, response: Response<FoodSubCategoryList>) {
                   if (response.isSuccessful){
                       recyclerFoodSubCategoryData.postValue(response.body())
                   }
               }

               override fun onFailure(call: Call<FoodSubCategoryList>, t: Throwable) {
                   recyclerFoodSubCategoryData.postValue(null)
               }

           })
    }


    /*Make Api call for the Food Sub Category Wise Data*/
    fun getFoodCategoryName(foodCategoryname:String){
        foodCategoryName = foodCategoryname
    }

    fun getFoodSubCategoryWiseObservable() : MutableLiveData<FoodShortDescList>{
        return recylerFoodShortDescData
    }

    fun MakeApiCallFoodSubCategoryWiseData(){

        val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
        val call = retroInstance.getFoodSubCategoryWiseList(foodCategoryName!!)
            call.enqueue(object : Callback<FoodShortDescList>{
                override fun onResponse(call: Call<FoodShortDescList>, response: Response<FoodShortDescList>) {
                    if (response.isSuccessful) {
                        recylerFoodShortDescData.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<FoodShortDescList>, t: Throwable) {
                    recylerFoodShortDescData.postValue(null)
                }

            })
    }

    /*Food Search*/
    fun getFoodSearchObservable() : MutableLiveData<FoodShortDescList>{
        return recylerFoodShortDescData
    }

    fun APICallSearchFood(searchText:String){
        val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
        val call = retroInstance.getHomeFoodSearchList(searchText)
            call.enqueue(object : Callback<FoodShortDescList>{
                override fun onResponse(call: Call<FoodShortDescList>, response: Response<FoodShortDescList>) {
                 if (response.isSuccessful) {
                    recylerFoodShortDescData.postValue(response.body())
                  }
                }

                override fun onFailure(call: Call<FoodShortDescList>, t: Throwable) {
                    recylerFoodShortDescData.postValue(null)

                }

            })
    }


}

