package com.utico.fooddelivery.view

import `in`.aabhasjindal.otptextview.OTPListener
import `in`.aabhasjindal.otptextview.OtpTextView
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.utico.fooddelivery.R
import com.utico.fooddelivery.databinding.ActivityOtpverficationBinding
import com.utico.fooddelivery.databinding.ItemRowPackageOrderHistoryBinding
import com.utico.fooddelivery.util.toast

class OTPVerficationActivity : AppCompatActivity() {
    private var otpTextView: OtpTextView? = null
    private var mobileNumber:String? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otpverfication)
        mobileNumber = intent.getStringExtra("mobileNumber")
        val tvMobileNumber = findViewById<TextView>(R.id.tv_mobile_number_description)
            tvMobileNumber.setText(mobileNumber)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = resources.getColor(R.color.black)
        }

        val verifyOTPButton = findViewById<Button>(R.id.btn_verify_otp)
        otpTextView = findViewById(R.id.otp_view)
        otpTextView?.requestFocusOTP()
        otpTextView?.otpListener = object : OTPListener {
            override fun onInteractionListener() {

            }

            override fun onOTPComplete(otp: String) {
                toast("The OTP is $otp")
            }
        }
        verifyOTPButton.setOnClickListener {
            otpTextView?.showSuccess()
            toast("OPT Verify Successful")
            val  intent = Intent(this,DashBoardActivity::class.java)
            startActivity(intent)
        }
    }
    }
