package com.utico.fooddelivery.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.utico.fooddelivery.retrofit.ApiService
import com.utico.fooddelivery.retrofit.RetroInstance
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository {
    fun userLogin(countryCode:String,mobileNumber:String):LiveData<String> {
        val loginResponse = MutableLiveData<String>()
        val restInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
        val call = restInstance.userLogin(countryCode,mobileNumber)
          call.enqueue(object : Callback<ResponseBody>{
              override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                  if(response.isSuccessful) {
                      loginResponse.value = response.body()?.string()
                  }else{
                      loginResponse.value = response.errorBody()?.string()
                  }
              }

              override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                  loginResponse.value = t.message
              }

          })
        return loginResponse
    }
    }