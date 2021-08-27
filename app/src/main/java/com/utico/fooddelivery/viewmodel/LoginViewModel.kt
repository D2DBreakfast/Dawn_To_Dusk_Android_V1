package com.utico.fooddelivery.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utico.fooddelivery.`interface`.LoginListener
import com.utico.fooddelivery.repositories.userRepository

class LoginViewModel : ViewModel() {

var loginListener:LoginListener? = null
    var mobile_number:String? = null
    val resulData = MutableLiveData<String>()

    fun onLoginButtonClick(view: View){
      val logiResponse = userRepository().userLogin("Sampath123@gmail.com","91",mobile_number!!)
        loginListener?.logIn(logiResponse)
    }

    fun getResultLogin():MutableLiveData<String>{
        return resulData
    }

    fun newRegistrationButtonClick(view: View){
        loginListener?.regiStration()
    }


}


