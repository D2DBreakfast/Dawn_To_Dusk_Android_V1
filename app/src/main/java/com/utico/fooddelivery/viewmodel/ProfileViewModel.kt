package com.utico.fooddelivery.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utico.fooddelivery.`interface`.ProfileListener
import com.utico.fooddelivery.model.ProfileFakeApi
import com.utico.fooddelivery.repositories.EditProfileRepository
import com.utico.fooddelivery.retrofit.ApiService
import com.utico.fooddelivery.retrofit.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel : ViewModel() {
    private var profileData: MutableLiveData<ProfileFakeApi>
    var errorResult = MutableLiveData<String>()
    var fullname:String? = null
    var mobileNumber:String? = null
    var email:String? = null
    var profileListener:ProfileListener? = null



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

    fun OnClickEditProfileButton(view:View){
       if (fullname.equals("") || fullname.equals(null)) {
           errorResult.value = "Please Provide the Name"
       }else if (email.equals("") || email.equals(null)){
           errorResult.value = "Please Provide the til_email"

       }else if(!(android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())){
           errorResult.value = "Please Provide the Valid Email Address"
       } else{
           val editProfileResponse = EditProfileRepository().EditProfile("9886230770",email!!,fullname!!)
            profileListener?.editProfile(editProfileResponse)
       }
    }
}