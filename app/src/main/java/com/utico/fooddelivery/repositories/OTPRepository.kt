package com.utico.fooddelivery.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.utico.fooddelivery.retrofit.ApiService
import com.utico.fooddelivery.retrofit.RetroInstance
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OTPRepository {
    fun otpVerfication(countryCode:String,mobileNumber:String,mobileOtp:String):LiveData<String>{
      val otpResponse  = MutableLiveData<String>()
      val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
      val call = retroInstance.otpVerification(countryCode,mobileNumber,mobileOtp)

          call.enqueue(object : Callback<ResponseBody>{
              override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    otpResponse.value = response.body()?.string()
                }else{
                    otpResponse.value = response.errorBody()?.string()
                }
              }

              override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                  otpResponse.value = t.message
              }

          })
        return otpResponse
    }
}