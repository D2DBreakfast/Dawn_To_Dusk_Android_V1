package com.utico.fooddelivery.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.AutoCompleteTextView
import android.widget.SearchView
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        viewModel.loginListener = this
        binding.loginviewmodel = viewModel

        viewModel.getResultLogin().observe(this, Observer {
            toast(it)
        })

    }

    override fun logIn(loginResponse: LiveData<String>) {
       loginResponse.observe(this, Observer {
           toast(it)
           val intent = Intent(this,OTPVerficationActivity::class.java)
           startActivity(intent)
       })

    }

    override fun regiStration() {
        val intent=Intent(this, RegistrationActivity::class.java)
          startActivity(intent)
    }

   /* override fun onCreateOptionsMenu(menu: Menu) : Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        val searchItem = menu.findItem(R.id.app_bar_search)
        if (searchItem!=null){
            val searchView = searchItem.actionView as SearchView
        }
        return super.onCreateOptionsMenu(menu)
    }*/

}

//https://www.taimoorsikander.com/phone-number-verification-otp-in-android-part-10/