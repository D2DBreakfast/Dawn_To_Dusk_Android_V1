package com.utico.fooddelivery.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.utico.fooddelivery.retrofit.ApiService
import com.utico.fooddelivery.retrofit.RetroInstance
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class registrationRepository {
   /* fun userRegistration(countryCode:String,fullName:String,mobileNumber:String,email:String):LiveData<String>{
      val registrationResponse = MutableLiveData<String>()
      val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)

      val call = retroInstance.userRegistration(countryCode,fullName,mobileNumber,email)
          call.enqueue(object : Callback<ResponseBody>{
              override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                  if (response.isSuccessful){
                      registrationResponse.value = response.body()?.string()
                  }else{
                      registrationResponse.value = response.errorBody()?.string()
                  }
              }

              override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                  registrationResponse.value = t.message

              }
          })
        return registrationResponse
    }*/


}