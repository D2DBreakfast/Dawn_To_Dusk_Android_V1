package com.utico.fooddelivery.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utico.fooddelivery.model.OrderCancelReasonResponseModel
import com.utico.fooddelivery.model.OrderCancelResponse
import com.utico.fooddelivery.model.PlacedOrderHistoryResponse
import com.utico.fooddelivery.retrofit.ApiService
import com.utico.fooddelivery.retrofit.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderHistoryViewModel : ViewModel() {
    private lateinit var orderHistoryResponse:MutableLiveData<PlacedOrderHistoryResponse>
    lateinit var orderCancelReasonResponse:MutableLiveData<OrderCancelReasonResponseModel>
    lateinit var orderCancelResponse:MutableLiveData<OrderCancelResponse>


    init {
        orderHistoryResponse = MutableLiveData()
        orderCancelReasonResponse = MutableLiveData()
        orderCancelResponse = MutableLiveData()
    }
   fun orderHistoryObservable(): MutableLiveData<PlacedOrderHistoryResponse>{
       return orderHistoryResponse
   }
    fun apiCallOrderHistory(userId:String){
      val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
      val call = retroInstance.orderHistory(userId)
          call.enqueue(object : Callback<PlacedOrderHistoryResponse>{
              override fun onResponse(call: Call<PlacedOrderHistoryResponse>, response: Response<PlacedOrderHistoryResponse>) {
                  if (response.isSuccessful){
                      orderHistoryResponse.postValue(response.body())
                  }
              }
              override fun onFailure(call: Call<PlacedOrderHistoryResponse>, t: Throwable) {
                  orderHistoryResponse.postValue(null)
              }
          })
    }
    /*Order Cancel Reason*/
    fun cancelReasonObservable():MutableLiveData<OrderCancelReasonResponseModel>{
        return orderCancelReasonResponse
    }
    fun apiCallForOrderCancelReason(){
        val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
        val call = retroInstance.orderCancelReason()
            call.enqueue(object : Callback<OrderCancelReasonResponseModel>{
                override fun onResponse(call: Call<OrderCancelReasonResponseModel>, response: Response<OrderCancelReasonResponseModel>) {
                    if (response.isSuccessful){
                        orderCancelReasonResponse.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<OrderCancelReasonResponseModel>, t: Throwable) {
                    orderHistoryResponse.postValue(null)
                }

            })
    }

    /*Placed Cancel*/
    fun placedOrderCancelObservable():MutableLiveData<OrderCancelResponse>{
        return orderCancelResponse
    }

    fun apiCallForOrderCancel(userId: String,orderId:String,reason:String){
        val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
        val call = retroInstance.placedOderCancel(userId,orderId,reason)
        call.enqueue(object : Callback<OrderCancelResponse>{
            override fun onResponse(call: Call<OrderCancelResponse>, response: Response<OrderCancelResponse>) {
                if (response.isSuccessful){
                    orderCancelResponse.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<OrderCancelResponse>, t: Throwable) {
                orderCancelResponse.postValue(null)
            }

        })
    }
}