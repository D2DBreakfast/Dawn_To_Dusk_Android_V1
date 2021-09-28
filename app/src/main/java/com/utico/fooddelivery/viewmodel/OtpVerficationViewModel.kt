package com.utico.fooddelivery.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utico.fooddelivery.`interface`.OTPVerificationListener
import com.utico.fooddelivery.repositories.OTPRepository

class OtpVerficationViewModel : ViewModel() {
    var otp:String? = null
    var resultData = MutableLiveData<String>()
    var errorResult = MutableLiveData<String>()
    var otpListener:OTPVerificationListener? = null

    fun getOTPtext(otptext:String){
        otp = otptext
    }
    fun onDashboardButtonClick(view:View){
        if (otp.equals("") || otp.equals(null)){
            errorResult.value = "Please Provide The OTP"
        }else{
            val otpResponse = OTPRepository().otpVerfication(otp!!,"9535347309","91")
             otpListener?.otp(otpResponse)
        }
    }

    fun getResponse() : MutableLiveData<String>{
       return resultData
    }
}