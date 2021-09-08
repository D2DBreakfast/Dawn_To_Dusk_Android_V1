package com.utico.fooddelivery.viewmodel

import android.provider.ContactsContract
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utico.fooddelivery.`interface`.RegistrationInterface
import com.utico.fooddelivery.repositories.registrationRepository
import com.utico.fooddelivery.util.Util

class RegistrationViewModel : ViewModel() {
    var registrationInterface:RegistrationInterface? = null
    var name: String? = null
    var email: String? = null
    var country_code:String? = null
    var mobilenumber: String? = null
    var resultData  = MutableLiveData<String>()
    var validationResultData = MutableLiveData<String>()

    fun onRegistrationButtonClick(view:View){
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
           val registerResponse =
               registrationRepository().userRegistration(name!!, email!!,"91",mobilenumber!!)
           registrationInterface?.registration(registerResponse)
       }
    }

    fun getResultRegistration():MutableLiveData<String>{
        return resultData
    }

}