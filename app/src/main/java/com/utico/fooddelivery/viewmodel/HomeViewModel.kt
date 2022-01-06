package com.utico.fooddelivery.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utico.fooddelivery.model.*
import com.utico.fooddelivery.retrofit.ApiService
import com.utico.fooddelivery.retrofit.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    lateinit var mainCategoryResponse:MutableLiveData<MainCategoryResponseModel>
    lateinit var recyclerSubCategoryData: MutableLiveData<SubCategoryResponseModel>
    lateinit var subscriptionTypeResponse: MutableLiveData<SubscriptionTitleDataResponseModel>
    lateinit var errResponse:MutableLiveData<String>


    var foodCategoryName: String? = null
    var mainCategoryId: String? = "1"

    init {
        mainCategoryResponse = MutableLiveData()
        recyclerSubCategoryData = MutableLiveData()
        subscriptionTypeResponse = MutableLiveData()
    }


   /* fun getMenuDetailObservable(): MutableLiveData<MenuDetailsResponseModel> {
        return menuDetailsResponseData
    }*/

    /*fun ApiCallMenuDetails(itemMainCategoryName: String) {
        val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
        val call = retroInstance.getMenuDetailsList(itemMainCategoryName)
        call.enqueue(object : Callback<MenuDetailsResponseModel> {
            override fun onResponse(
                call: Call<MenuDetailsResponseModel>, response: Response<MenuDetailsResponseModel>
            ) {
                if (response.isSuccessful) {
                    menuDetailsResponseData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<MenuDetailsResponseModel>, t: Throwable) {
                errResponse.postValue(t.message)
            }

        })
    }

    *//*This method is Used to get the Veg Related Menu Data*//*
    fun getVegMenuDetailsObservable(): MutableLiveData<SubCategoryMenuDetailsModel> {
        return subCategoruMenudetailsResponse
    }*/

   /* fun apiCallVegDetails(
        itemMainCategoryName: String,
        itemSubCategoryName: String,
        itemFoodType: String
    ) {
        val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
        val call =
            retroInstance.getVegMenuDetails(itemMainCategoryName, itemSubCategoryName, itemFoodType)
        call.enqueue(object : Callback<SubCategoryMenuDetailsModel> {
            override fun onResponse(
                call: Call<SubCategoryMenuDetailsModel>,
                response: Response<SubCategoryMenuDetailsModel>
            ) {
                if (response.isSuccessful) {
                    subCategoruMenudetailsResponse.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<SubCategoryMenuDetailsModel>, t: Throwable) {
                subCategoruMenudetailsResponse.postValue(null)
            }

        })
    }
*/

    /*get the Main Category list of data*/
    fun mainCategoryObservable():MutableLiveData<MainCategoryResponseModel>{
        return mainCategoryResponse
    }

    fun apiCallForMainCategoryListing(){
        val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
        val call = retroInstance.fetchMainCategory()
            call.enqueue(object :Callback<MainCategoryResponseModel>{
                override fun onResponse(call: Call<MainCategoryResponseModel>, response: Response<MainCategoryResponseModel>) {
                    if (response.isSuccessful){
                        mainCategoryResponse.postValue(response.body())
                    }
                }
                override fun onFailure(call: Call<MainCategoryResponseModel>, t: Throwable) {
                    mainCategoryResponse.postValue(null)
                }
            })
    }


    /*Get the food sub Category list function*/
    fun getSubCategoryObservable(): MutableLiveData<SubCategoryResponseModel> {
        return recyclerSubCategoryData
    }

    /* Make API Call for getting the food subcategory List of data*/
    fun APICallSubCategory(mainCategoryId: String) {
        val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
        val call = retroInstance.getSubCategoryList(mainCategoryId)
        call.enqueue(object : Callback<SubCategoryResponseModel> {
            override fun onResponse(
                call: Call<SubCategoryResponseModel>,
                response: Response<SubCategoryResponseModel>
            ) {
                if (response.isSuccessful) {
                    recyclerSubCategoryData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<SubCategoryResponseModel>, t: Throwable) {
                errResponse.postValue(t.message)
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


   /* fun APICallSearchFood(searchText: String) {
        val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
        val call = retroInstance.getHomeFoodSearchList(searchText)
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
    }*/

/*

    */
/*SubCategory Menus Details Model method*//*

    fun subCategoryMenuDetailsObservable(): MutableLiveData<SubCategoryMenuDetailsModel> {
        return subCategoruMenudetailsResponse
    }

    fun apiCallSubCategoryMenuDetails(itemMainCategoryName: String, itemSubCategoryName: String) {
        val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
        val call =
            retroInstance.getSubCategoryMenusDetails(itemMainCategoryName, itemSubCategoryName)
        call.enqueue(object : Callback<SubCategoryMenuDetailsModel> {
            override fun onResponse(
                call: Call<SubCategoryMenuDetailsModel>,
                response: Response<SubCategoryMenuDetailsModel>
            ) {
                if (response.isSuccessful) {
                    subCategoruMenudetailsResponse.postValue(response.body())
                }

            }

            override fun onFailure(call: Call<SubCategoryMenuDetailsModel>, t: Throwable) {
                subCategoruMenudetailsResponse.postValue(null)
            }
        })
    }
*/

    /*Get subscription Type*/
    fun subscriptionTypeObservable(): MutableLiveData<SubscriptionTitleDataResponseModel> {
        return subscriptionTypeResponse
    }

    fun apiCallSubscriptionTypes() {
        val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
        val call = retroInstance.getSubscriptionTypes()
        call.enqueue(object : Callback<SubscriptionTitleDataResponseModel> {
            override fun onResponse(
                call: Call<SubscriptionTitleDataResponseModel>,
                response: Response<SubscriptionTitleDataResponseModel>
            ) {
                if (response.isSuccessful) {
                    subscriptionTypeResponse.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<SubscriptionTitleDataResponseModel>, t: Throwable) {
                subscriptionTypeResponse.postValue(null)

            }

        })
    }
}

