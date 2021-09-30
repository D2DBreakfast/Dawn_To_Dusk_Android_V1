package com.utico.fooddelivery.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.utico.fooddelivery.model.EditProfileResponse
import com.utico.fooddelivery.retrofit.ApiService
import com.utico.fooddelivery.retrofit.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfileRepository {
    fun EditProfile(name:String,email:String):LiveData<String>{
            val editResponse = MutableLiveData<String>()
        val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
        val call = retroInstance.EditUserProfile(name,email)
            call.enqueue(object : Callback<EditProfileResponse>{
                override fun onResponse(call: Call<EditProfileResponse>, response: Response<EditProfileResponse>) {
                  if (response.isSuccessful){
                      editResponse.value = response.body().toString()
                  }else{
                      editResponse.value = response.errorBody().toString()
                  }
                }

                override fun onFailure(call: Call<EditProfileResponse>, t: Throwable) {
                    editResponse.value = t.message
                }
            })
        return editResponse
    }
}