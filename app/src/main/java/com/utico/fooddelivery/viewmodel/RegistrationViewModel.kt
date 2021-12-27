package com.utico.fooddelivery.viewmodel

import android.provider.ContactsContract
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utico.fooddelivery.`interface`.RegistrationInterface
import com.utico.fooddelivery.model.UserRegistrationResponseModel
import com.utico.fooddelivery.repositories.registrationRepository
import com.utico.fooddelivery.retrofit.ApiService
import com.utico.fooddelivery.retrofit.RetroInstance
import com.utico.fooddelivery.util.Util
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationViewModel : ViewModel() {
    var registrationInterface:RegistrationInterface? = null
    var name: String? = null
    var email: String? = null
    var country_code:String? = null
    var mobilenumber: String? = null
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

        if (name.equals("") || name.equals(null)){
            validationResultData.value = "Please Provide Full Name"
        } else if(email.equals("") || email.equals(null)){
            validationResultData.value = "Please Provide Email"
        }else if (mobilenumber.equals("") || mobilenumber.equals(null)){
            validationResultData.value = "Please Provide Mobile Number"
        } else if(mobilenumber!!.length < 9){
            validationResultData.value = "Please Provide Valid Mobile Number"
        }else if (!(android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()))
            validationResultData.value = "Please Provide Valid Email Id"
        else {
            val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
            val call =
                retroInstance.userRegistration(country_code!!, name!!, mobilenumber!!, email!!)
            call.enqueue(object : Callback<UserRegistrationResponseModel> {
                override fun onResponse(
                    call: Call<UserRegistrationResponseModel>,
                    response: Response<UserRegistrationResponseModel>
                ) {
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


    /*fun onRegistrationButtonClick(view:View){
       if (name.equals("") || name.equals(null)){
           validationResultData.value = "Please Provide Full Name"
       } else if(email.equals("") || email.equals(null)){
           validationResultData.value = "Please Provide Email"
       }else if (mobilenumber.equals("") || mobilenumber.equals(null)){
           validationResultData.value = "Please Provide Mobile Number"
       } else if(mobilenumber!!.length < 10){
           validationResultData.value = "Please Provide Valid Mobile Number"
       }else if (!(android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()))
           validationResultData.value = "Please Provide Valid Email Id"
       else{
         *//*  val registerResponse =
               registrationRepository().userRegistration(country_code!!,name!!,mobilenumber!!,email!!)
           registrationInterface?.registration(registerResponse)*//*
       }
    }*/

    fun getResultRegistration():MutableLiveData<String>{
        return resultData
    }


}