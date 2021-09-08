package com.utico.fooddelivery.viewmodel

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.utico.fooddelivery.`interface`.LoginListener
import com.utico.fooddelivery.repositories.userRepository

class LoginViewModel : ViewModel() {

var loginListener:LoginListener? = null
    var mobile_number:String? = null
    var resulData = MutableLiveData<String>()
    var errorResultData = MutableLiveData<String>()

    fun onLoginButtonClick(view: View){
      if (mobile_number.equals("") || mobile_number.equals(null))  {
            errorResultData.value ="Please Provide Phone Number"

      }else if (mobile_number!!.length < 10 || mobile_number!!.length > 13){
          errorResultData.value ="Please Provide the Valid Phone Number"
      } else{
          val logiResponse = userRepository().userLogin("Sampath123@gmail.com","91",mobile_number!!)
          loginListener?.logIn(logiResponse)
      }

    }

    fun getResultLogin():MutableLiveData<String>{
        return resulData
    }

    fun newRegistrationButtonClick(view: View){
        loginListener?.regiStration()
    }


}





