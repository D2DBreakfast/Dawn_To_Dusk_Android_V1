package com.utico.fooddelivery.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.utico.fooddelivery.model.EditProfileResponse
import com.utico.fooddelivery.model.EditProfileresponse
import com.utico.fooddelivery.retrofit.ApiService
import com.utico.fooddelivery.retrofit.RetroInstance
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfileRepository {
    fun EditProfile(mobileNumber:String,fullName:String,email:String):LiveData<String>{
            val editResponse = MutableLiveData<String>()
        val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
        val call = retroInstance.EditUserProfile(mobileNumber,fullName,email)
            call.enqueue(object : Callback<ResponseBody>{
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                  if (response.isSuccessful){
                      editResponse.value = response.body().toString()
                  }else{
                      editResponse.value = response.errorBody().toString()
                  }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    editResponse.value = t.message
                }
            })
        return editResponse
    }
}