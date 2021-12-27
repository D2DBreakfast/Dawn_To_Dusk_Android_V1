package com.utico.fooddelivery.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utico.fooddelivery.model.OrderPlacedResponse
import com.utico.fooddelivery.model.SubscriptionPlansResponseModel
import com.utico.fooddelivery.model.SubscriptionUpcomingMealResponseModel
import com.utico.fooddelivery.retrofit.ApiService
import com.utico.fooddelivery.retrofit.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubscriptionDetailsViewModel : ViewModel() {
    private lateinit var subscriptionUpcomingMealsResponse: MutableLiveData<SubscriptionUpcomingMealResponseModel>
    private lateinit var subscriptionPlansResponse: MutableLiveData<SubscriptionPlansResponseModel>
    private lateinit var placeSubscriptionResponse: MutableLiveData<OrderPlacedResponse>

    init {
        subscriptionUpcomingMealsResponse = MutableLiveData()
        subscriptionPlansResponse = MutableLiveData()
        placeSubscriptionResponse = MutableLiveData()
    }

    fun subscriptionUpcomingMealObservable(): MutableLiveData<SubscriptionUpcomingMealResponseModel> {
        return subscriptionUpcomingMealsResponse
    }

    fun apiCallUpcomingMeals(subscriptionId: String) {
        val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
        val call = retroInstance.subscriptionUpcomingMeals(subscriptionId)
        call.enqueue(object : Callback<SubscriptionUpcomingMealResponseModel> {
            override fun onResponse(
                call: Call<SubscriptionUpcomingMealResponseModel>,
                response: Response<SubscriptionUpcomingMealResponseModel>
            ) {
                if (response.isSuccessful) {
                    subscriptionUpcomingMealsResponse.postValue(response.body())
                }
            }

            override fun onFailure(
                call: Call<SubscriptionUpcomingMealResponseModel>,
                t: Throwable
            ) {
                subscriptionUpcomingMealsResponse.postValue(null)

            }

        })
    }


    fun subscriptionPlanObservable(): MutableLiveData<SubscriptionPlansResponseModel> {
        return subscriptionPlansResponse
    }

    fun apiCallPlan(subscriptionId: String) {
        val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
        val call = retroInstance.subscriptionPlans(subscriptionId)
        call.enqueue(object : Callback<SubscriptionPlansResponseModel> {
            override fun onResponse(
                call: Call<SubscriptionPlansResponseModel>,
                response: Response<SubscriptionPlansResponseModel>
            ) {
                if (response.isSuccessful) {
                    subscriptionPlansResponse.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<SubscriptionPlansResponseModel>, t: Throwable) {
                subscriptionPlansResponse.postValue(null)

            }
        })
    }

    /*Place the subscription Plan*/
    fun placeSubscriptionObservable(): MutableLiveData<OrderPlacedResponse> {
        return placeSubscriptionResponse
    }

    fun apiCallPlaceSubscriptionPlan(
        userId: String, sectorId: String,
        villa: String, landMark: String, totalAmount: String,
        categoryType: String, plan: String, startDate: String,
        subscriptionType: String
    ) {
        val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
        val call = retroInstance.placeSubscription(userId,sectorId, villa, landMark,
            totalAmount, categoryType, plan, startDate, subscriptionType)
        call.enqueue(object : Callback<OrderPlacedResponse> {
            override fun onResponse(call: Call<OrderPlacedResponse>, response: Response<OrderPlacedResponse>) {
                if (response.isSuccessful) {
                    placeSubscriptionResponse.postValue(response.body())
                }
            }
            override fun onFailure(call: Call<OrderPlacedResponse>, t: Throwable) {
                placeSubscriptionResponse.postValue(null)
            }

        })
    }


}