package com.utico.fooddelivery.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utico.fooddelivery.model.NotificationList
import com.utico.fooddelivery.retrofit.ApiService
import com.utico.fooddelivery.retrofit.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationsViewModel : ViewModel() {

    lateinit var recyclerNotificationData:MutableLiveData<NotificationList>

    init {
        recyclerNotificationData = MutableLiveData()
    }

    fun getNoticationObserable() : MutableLiveData<NotificationList>{
      return recyclerNotificationData
    }

    fun makeApiCallNotificationList(){
        val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
        val call = retroInstance.getAllNotification()
          call.enqueue(object : Callback<NotificationList>{
              override fun onResponse(call: Call<NotificationList>, response: Response<NotificationList>) {
                  if (response.isSuccessful){
                      recyclerNotificationData.postValue(response.body())
                  }
              }

              override fun onFailure(call: Call<NotificationList>, t: Throwable) {
                  recyclerNotificationData.postValue(null)
              }

          })

    }

}