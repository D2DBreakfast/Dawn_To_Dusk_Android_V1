package com.utico.fooddelivery.viewmodel

import android.content.SharedPreferences
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utico.fooddelivery.R
import com.utico.fooddelivery.`interface`.OTPVerificationListener
import com.utico.fooddelivery.repositories.OTPRepository

class OtpVerficationViewModel : ViewModel() {
    var otp:String? = null
    var resultData = MutableLiveData<String>()
    var errorResult = MutableLiveData<String>()
    var otpListener:OTPVerificationListener? = null
    var countryCode:String? = null
    var mobileNumber:String? = null

    fun getOTPtext(otptext:String){
        otp = otptext
    }

    fun getRegistrationData(mobile_Number:String,country_code:String){
        mobileNumber = mobile_Number
        countryCode = country_code
    }
    fun onDashboardButtonClick(view:View){
        if (otp.equals("") || otp.equals(null)){
            errorResult.value = "Please Provide The OTP"
        }else{
            val otpResponse = OTPRepository().otpVerfication(countryCode!!,mobileNumber!!,otp!!)
             otpListener?.otp(otpResponse)
        }
    }

    fun getResponse() : MutableLiveData<String>{
       return resultData
    }
}