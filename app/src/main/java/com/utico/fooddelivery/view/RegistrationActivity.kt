package com.utico.fooddelivery.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import com.utico.fooddelivery.R
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

        viewModel.errorResponse.observe(this, Observer {
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

        binding.tvLogin.setOnClickListener {
           /* binding.viewLineLogin.visibility = View.VISIBLE
            binding.viewLineRegister.visibility =View.INVISIBLE
            binding.fullNameTextInputLayout.visibility = View.GONE
            binding.emailTextInputLayout.visibility = View.GONE
            binding.tvLogin.text=this.resources.getString(R.string.tv_label_login)
            binding.tvHeaderName.text=this.resources.getString(R.string.tv_label_login)
            binding.btnSubmit.text=this.resources.getString(R.string.tv_label_login)*/

             val intent = Intent(this,LoginActivity::class.java)
                 startActivity(intent)
                 finish()
        }
        binding.tvRegister.setOnClickListener {
            binding.viewLineLogin.visibility = View.INVISIBLE
            binding.viewLineRegister.visibility =View.VISIBLE
            binding.fullNameTextInputLayout.visibility = View.VISIBLE
            binding.emailTextInputLayout.visibility = View.VISIBLE
            binding.tvLogin.text=this.resources.getString(R.string.tv_label_login)
            binding.tvHeaderName.text=this.resources.getString(R.string.tv_label_registration)
            binding.btnSubmit.text=this.resources.getString(R.string.tv_label_registration)


        }

    }

    fun clickSubmitButton(){
       binding.btnSubmit.setOnClickListener {
           //start temp
          /* editor.putString("fullName","Shivanad Patil")
           editor.putString("mobileNumber","9535347309")
           editor.putString("email","patil123@gmail.com")
           editor.putString("countryCode","patil123@gmail.com")
           editor.putString("userId","2")
           editor.commit()
           val intent = Intent(this,OtpVerficationActivity::class.java)
           intent.putExtra("mobileNumber","Please type the verification code sent to" +" "+mobileNumber)
           startActivity(intent)//temp Remove later*/

           viewModel.getRegistrationObservable().observe(this, Observer<UserRegistrationResponseModel> {
               if (it.statusCode == 400){
                   toast(it.message)
                  /* editor.putString("fullName",it.RegisterData.fullName)
                   editor.putString("mobileNumber",it.RegisterData.mobileNo)
                   editor.putString("email",it.RegisterData.email)
                   editor.putString("countryCode",it.RegisterData.countryCode)
                   editor.putString("userId",it.RegisterData.userId)
                   editor.commit()*/
                 /*  val intent = Intent(this,OtpVerficationActivity::class.java)
                   intent.putExtra("mobileNumber","Please type the verification code sent to" +" "+mobileNumber)
                   startActivity(intent)*/
               }else{
                   toast(it.RegisterData.fullName)
                   editor.putString("fullName",it.RegisterData.fullName)
                   editor.putString("mobileNumber",it.RegisterData.mobileNo)
                   editor.putString("email",it.RegisterData.email)
                   editor.putString("countryCode",it.RegisterData.countryCode)
                   editor.putString("userId",it.RegisterData.userId)
                   editor.commit()
                    val intent = Intent(this,OtpVerficationActivity::class.java)
                     intent.putExtra("mobileNumber","Please type the verification code sent to" +" "+it.RegisterData.mobileNo)
                     startActivity(intent)
                  // toast(it.RegisterData.fullName +it.RegisterData.mobileNo + it.RegisterData.email +it.RegisterData.countryCode + it.RegisterData.userId)
               }
           })
           viewModel.ApiCall()
       }
    }


}