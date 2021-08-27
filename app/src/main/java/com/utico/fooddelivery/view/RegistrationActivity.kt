package com.utico.fooddelivery.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import com.utico.fooddelivery.R
import com.utico.fooddelivery.`interface`.RegistrationInterface
import com.utico.fooddelivery.databinding.ActivityRegistrationBinding
import com.utico.fooddelivery.util.toast
import com.utico.fooddelivery.viewmodel.RegistrationViewModel

class RegistrationActivity : AppCompatActivity(), RegistrationInterface {
      private lateinit var binding: ActivityRegistrationBinding
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
    }


    override fun registration(registerResponse: LiveData<String>) {
        registerResponse.observe(this,Observer{
            toast(it)
            val intent = Intent(this,DashBoardActivity::class.java)
            startActivity(intent)

        })

    }
}