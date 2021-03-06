package com.utico.fooddelivery.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.utico.fooddelivery.R
import com.utico.fooddelivery.`interface`.LoginListener
import com.utico.fooddelivery.databinding.ActivityLoginBinding
import com.utico.fooddelivery.util.toast
import com.utico.fooddelivery.viewmodel.LoginViewModel


class LoginActivity : AppCompatActivity(),LoginListener {
    private lateinit var binding: ActivityLoginBinding
    lateinit var viewModel: LoginViewModel
    var mobileNumber:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        viewModel.loginListener = this
        binding.loginviewmodel = viewModel
        val countryCodeArray = resources.getStringArray(R.array.countryCode_array)


        viewModel.getResultLogin().observe(this, Observer {
            toast(it)
        })

        viewModel.errorResultData.observe(this, Observer {
            toast(it)
        })



        val editText_mobile_no = binding.textinputeditMobileNo
            editText_mobile_no.doOnTextChanged { text, start, before, count ->
                if (text!!.length < 9){
                    editText_mobile_no.error = "Please Provide the Valid Phone Number"
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
            binding.viewLineRegister.visibility = View.INVISIBLE
            binding.viewLineLogin.visibility = View.VISIBLE
        }

        binding.tvRegister.setOnClickListener {
            binding.viewLineLogin.visibility = View.INVISIBLE
            binding.viewLineRegister.visibility = View.VISIBLE
            val intent=Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun logIn(loginResponse: LiveData<String>) {
       loginResponse.observe(this, Observer {
           toast(it)
           val intent = Intent(this,OtpVerficationActivity::class.java)
               intent.putExtra("mobileNumber","Please type the verification code sent to" +" "+mobileNumber)
           startActivity(intent)
       })

    }

    override fun regiStration() {
       /* val intent=Intent(this, RegistrationActivity::class.java)
          startActivity(intent)*/
    }



}


//https://android--code.blogspot.com/2020/09/android-kotlin-edittext-phone-number.html

