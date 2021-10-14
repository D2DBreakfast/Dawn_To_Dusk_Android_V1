package com.utico.fooddelivery.view

import `in`.aabhasjindal.otptextview.OTPListener
import `in`.aabhasjindal.otptextview.OtpTextView
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.utico.fooddelivery.R
import com.utico.fooddelivery.`interface`.OTPVerificationListener
import com.utico.fooddelivery.databinding.ActivityOtpverficationBinding
import com.utico.fooddelivery.util.toast
import com.utico.fooddelivery.viewmodel.OtpVerficationViewModel

class OtpVerficationActivity : AppCompatActivity(),OTPVerificationListener {
    private var otpTextView: OtpTextView? = null
    private var mobileNumber:String? =null
    private lateinit var binding: ActivityOtpverficationBinding
    private lateinit var viewMode: OtpVerficationViewModel
    lateinit var sharedPreferences: SharedPreferences
    var mobile_number:String? = null
    var countryCode:String? = null
    var counter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_otpverfication)
        sharedPreferences=getSharedPreferences(resources.getString(R.string.registration_details_sharedPreferences), Context.MODE_PRIVATE)
        mobile_number = sharedPreferences.getString("mobileNumber","")
        countryCode = sharedPreferences.getString("countryCode","")
        binding = DataBindingUtil.setContentView(this,R.layout.activity_otpverfication)
        viewMode = ViewModelProviders.of(this,).get(OtpVerficationViewModel::class.java)
        viewMode.otpListener = this
        binding.otpViewModel = viewMode

        startTimerCounter()

        mobileNumber = intent.getStringExtra("mobileNumber")
        val tvMobileNumber = findViewById<TextView>(R.id.tv_mobile_number_description)
            tvMobileNumber.setText(mobileNumber)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = resources.getColor(R.color.black)
        }


        otpTextView = findViewById(R.id.otp_view)
        otpTextView?.requestFocusOTP()
        otpTextView?.otpListener = object : OTPListener {
            override fun onInteractionListener() {

            }

            override fun onOTPComplete(otp: String) {
                toast("The OTP is $otp")
                viewMode.getOTPtext(otp)
            }
        }

       viewMode.getRegistrationData(mobile_number!!,countryCode!!)
        viewMode.errorResult.observe(this, Observer {
            toast(it)
        })

       viewMode.getResponse().observe(this, Observer {
           toast(it)

       })


    }

    override fun otp(otpResponse: LiveData<String>) {
       otpResponse.observe(this, Observer {
           toast(it)
           val intent = Intent(this,DashBoardActivity::class.java)
               startActivity(intent)
       })
    }

    fun startTimerCounter(){
       object : CountDownTimer(120000,1000){
           override fun onTick(millisUntilFinished: Long) {
               val tvResend = binding.tvResend
               tvResend.text = "Resend"+" " + counter.toString()
               counter++
           }
           override fun onFinish() {
               val tvResend = binding.tvResend
               tvResend.text = "Finished"

           }
       }.start()
       }
/*
    https://www.tutorialspoint.com/ho
*/


}
