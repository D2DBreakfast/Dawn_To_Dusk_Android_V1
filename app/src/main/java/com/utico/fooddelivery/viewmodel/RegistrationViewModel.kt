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

    fun onRegistrationButtonClick(view:View){
        var validationMessage:String = ""
        if(name.equals("")){
           validationMessage="Name Field is Required"
        } else if (email.equals("")){
            validationMessage="Email Field is Required"
        } else if(mobilenumber.equals("")){
            validationMessage="Mobile Number is Required"
        }else {
            val registerResponse =
                registrationRepository().userRegistration(name!!, email!!,"91",mobilenumber!!)
            registrationInterface?.registration(registerResponse)
        }
    }

    fun getResultRegistration():MutableLiveData<String>{
        return resultData
    }

}