package com.utico.fooddelivery.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import com.utico.fooddelivery.R
import com.utico.fooddelivery.`interface`.RegistrationInterface
import com.utico.fooddelivery.databinding.ActivityRegistrationBinding
import com.utico.fooddelivery.util.toast
import com.utico.fooddelivery.viewmodel.RegistrationViewModel

class RegistrationActivity : AppCompatActivity(), RegistrationInterface {
      private lateinit var binding: ActivityRegistrationBinding
      var mobileNumber:String? = null
      lateinit var viewModel: RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_registration

        binding = DataBindingUtil.setContentView(this,R.layout.activity_registration)
        viewModel = ViewModelProviders.of(this).get(RegistrationViewModel::class.java)
        viewModel.registrationInterface = this
        binding.registrationViewModel = viewModel
        viewModel.getResultRegistration().observe(this, Observer {
            toast(it)
        })

        viewModel.validationResultData.observe(this, Observer {
           toast(it)
        })


        var fullname = binding.fullNameTextInputEdt
             fullname.doOnTextChanged { text, start, before, count ->
                 if (text!!.isEmpty()){
                   fullname.error = "Full Name is Required"
                 }
             }

        var email = binding.emailTextInputEdt
            email.doOnTextChanged { text, start, before, count ->
                if (!(android.util.Patterns.EMAIL_ADDRESS.matcher(text!!).matches())){
                    email.error = "Pleas Provide The Valid Email ID"
                }
            }

        var phoneNumber = binding.mobileNoTextInputEdt
            phoneNumber.doOnTextChanged { text, start, before, count ->
                if (text!!.length < 10){
                    phoneNumber.error ="Please Provide The Valid Phone Number"
                }
                mobileNumber = text!!.toString()
            }



        val countryCode = resources.getStringArray(R.array.countryCode_array)
        val arrayAdapter = ArrayAdapter(this,R.layout.dropdown_item,countryCode)
        binding.countryCode.setAdapter(arrayAdapter)

        binding.countryCode.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val selectedItem = parent.getItemAtPosition(position) as String
                toast(selectedItem)
            }

    }


    override fun registration(registerResponse: LiveData<String>) {
        registerResponse.observe(this,Observer{
            toast(it)
            val intent = Intent(this,OtpVerficationActivity::class.java)
            intent.putExtra("mobileNumber","Please type the verification code sent to" +" "+mobileNumber)
            startActivity(intent)

        })
    }
}