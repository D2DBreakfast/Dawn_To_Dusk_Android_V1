package com.utico.fooddelivery.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utico.fooddelivery.`interface`.RegistrationInterface
import com.utico.fooddelivery.model.UserRegistrationResponseModel
import com.utico.fooddelivery.retrofit.ApiService
import com.utico.fooddelivery.retrofit.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationViewModel : ViewModel() {
    var registrationInterface:RegistrationInterface? = null
    var fullName: String? = null
    var email: String? = ""
    var countryCode:String? = null
    var mobileNumber: String? = null
    var resultData  = MutableLiveData<String>()
    var validationResultData = MutableLiveData<String>()
    lateinit var registrationResponseData:MutableLiveData<UserRegistrationResponseModel>
    lateinit var errorResponse:MutableLiveData<String>



    init {
        registrationResponseData = MutableLiveData()
        errorResponse = MutableLiveData()
    }

    fun getRegistrationObservable():MutableLiveData<UserRegistrationResponseModel>{
        return registrationResponseData
    }

    fun ApiCall(){

        if (fullName.equals("") || fullName.equals(null)){
            validationResultData.value = "Please Provide Full Name"
        } else if (mobileNumber.equals("") || mobileNumber.equals(null)){
            validationResultData.value = "Please Provide Mobile Number"
        } else if(mobileNumber!!.length < 9){
            validationResultData.value = "Please Provide Valid Mobile Number"
        }
       /* else if (!(android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()))
            validationResultData.value = "Please Provide Valid Email Id"*/
        else {
            val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
            val call = retroInstance.userRegistration(fullName!!,mobileNumber!!,countryCode!!,email!!)
            call.enqueue(object : Callback<UserRegistrationResponseModel> {
                override fun onResponse(call: Call<UserRegistrationResponseModel>, response: Response<UserRegistrationResponseModel>) {
                    if (response.isSuccessful) {
                        registrationResponseData.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<UserRegistrationResponseModel>, t: Throwable) {
                    registrationResponseData.postValue(null)
                    errorResponse.postValue(t.toString())
                }
            })
        }
    }


    fun getResultRegistration():MutableLiveData<String>{
        return resultData
    }


}