package com.utico.fooddelivery.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
import com.utico.fooddelivery.model.UserRegistrationResponseModel
import com.utico.fooddelivery.util.toast
import com.utico.fooddelivery.viewmodel.RegistrationViewModel

class RegistrationActivity : AppCompatActivity(){
      private lateinit var binding: ActivityRegistrationBinding
      var mobileNumber:String? = null
      lateinit var viewModel: RegistrationViewModel
      lateinit var sharedPreferences: SharedPreferences
      lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_registration

        binding = DataBindingUtil.setContentView(this,R.layout.activity_registration)
        viewModel = ViewModelProviders.of(this).get(RegistrationViewModel::class.java)
       /* viewModel.registrationInterface = this*/
        binding.registrationViewModel = viewModel
        clickSubmitButton()
        /*sharedPreferences  registration response data*/
        sharedPreferences=getSharedPreferences(resources.getString(R.string.registration_details_sharedPreferences), Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        viewModel.getResultRegistration().observe(this, Observer {
            toast(it)
        })

        viewModel.validationResultData.observe(this, Observer {
           toast(it)
        })


        var fullname = binding.tilFullName
             fullname.doOnTextChanged { text, start, before, count ->
                 if (text!!.isEmpty()){
                   fullname.error = "Full Name is Required"
                 }
             }

        var email = binding.tilEmail
            email.doOnTextChanged { text, start, before, count ->
                if (!(android.util.Patterns.EMAIL_ADDRESS.matcher(text!!).matches())){
                    email.error = "Pleas Provide The Valid Email ID"
                }
            }

        var phoneNumber = binding.tilMobileNumber
            phoneNumber.doOnTextChanged { text, start, before, count ->
                if (text!!.length < 9){
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


   /* override fun registration(registerResponse: LiveData<String>) {
        registerResponse.observe(this,Observer{
            toast(it)
            editor.putString("fullName",binding.tilFullName.text.toString())
            editor.putString("mobileNumber",binding.tilMobileNumber.text.toString())
            editor.putString("email",binding.tilEmail.text.toString())
            editor.putString("countryCode",binding.countryCode.text.toString())
            editor.commit()
            val intent = Intent(this,OtpVerficationActivity::class.java)
            intent.putExtra("mobileNumber","Please type the verification code sent to" +" "+mobileNumber)
            startActivity(intent)

        })
    }*/

    fun clickSubmitButton(){
       binding.btnRegistration.setOnClickListener {
           viewModel.getRegistrationObservable().observe(this, Observer<UserRegistrationResponseModel> {
               if (it.statusCode == 200){
                   toast(it.message)
                   editor.putString("fullName",it.loginData.fullName)
                   editor.putString("mobileNumber",it.loginData.mobileNo)
                   editor.putString("email",it.loginData.email)
                   editor.putString("countryCode",it.loginData.countryCode)
                   editor.putString("userId",it.loginData.userId)
                   editor.commit()
                   val intent = Intent(this,OtpVerficationActivity::class.java)
                   intent.putExtra("mobileNumber","Please type the verification code sent to" +" "+mobileNumber)
                   startActivity(intent)
               }
           })
           viewModel.ApiCall()
       }
    }
}