package com.utico.fooddelivery.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utico.fooddelivery.model.ProfileFakeApi
import com.utico.fooddelivery.model.profile
import com.utico.fooddelivery.retrofit.ApiService
import com.utico.fooddelivery.retrofit.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileEditViewModel : ViewModel() {
    lateinit var profileData: MutableLiveData<ProfileFakeApi>
    var name:String? = null
    var email:String? = null
    var mobileNumber:String? = null


    init {
        profileData = MutableLiveData()
    }


    fun getProfileObserable():MutableLiveData<ProfileFakeApi>{
      return profileData
    }

    fun ApiCallProfile(){
       val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
       val call = retroInstance.getProfile()
           call.enqueue(object : Callback<ProfileFakeApi>{
               override fun onResponse(call: Call<ProfileFakeApi>, response: Response<ProfileFakeApi>) {
                   if (response.isSuccessful){
                       profileData.postValue(response.body())


                   }
               }

               override fun onFailure(call: Call<ProfileFakeApi>, t: Throwable) {
                   profileData.postValue(null)
               }

           })
    }
}