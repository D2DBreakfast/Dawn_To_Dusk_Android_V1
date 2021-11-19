package com.utico.fooddelivery.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utico.fooddelivery.model.ProfileEditResponseModel
import com.utico.fooddelivery.retrofit.ApiService
import com.utico.fooddelivery.retrofit.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel : ViewModel() {
    private var profileUpdateResponseData: MutableLiveData<ProfileEditResponseModel>
    var errorResult = MutableLiveData<String>()
    var fullname:String? = null
    var mobileNumber:String? = null
    var email:String? = null
    var userId:String? = null




    init {
        profileUpdateResponseData = MutableLiveData()
    }


    fun editProfileObservable():MutableLiveData<ProfileEditResponseModel>{
      return profileUpdateResponseData
    }

    fun ApiCallEditProfile(){
       val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
       val call = retroInstance.userprofileUpdate(fullname!!,email!!,userId!!)
           call.enqueue(object : Callback<ProfileEditResponseModel>{
               override fun onResponse(call: Call<ProfileEditResponseModel>, response: Response<ProfileEditResponseModel>) {
                   if (response.isSuccessful){
                       profileUpdateResponseData.postValue(response.body())

                   }
               }

               override fun onFailure(call: Call<ProfileEditResponseModel>, t: Throwable) {
                   profileUpdateResponseData.postValue(null)
               }

           })
    }

   /* fun OnClickEditProfileButton(view:View){
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
    }*/
}