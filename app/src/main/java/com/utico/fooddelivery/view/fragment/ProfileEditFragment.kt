package com.utico.fooddelivery.view.fragment

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.utico.fooddelivery.R
import com.utico.fooddelivery.databinding.ProfileEditFragmentBinding
import com.utico.fooddelivery.model.ProfileEditResponseModel
import com.utico.fooddelivery.viewmodel.ProfileViewModel

class ProfileEditFragment : Fragment(){

    companion object {
        fun newInstance() = ProfileEditFragment()
    }

    private lateinit var viewModel: ProfileViewModel
    private lateinit var binding: ProfileEditFragmentBinding
    private lateinit var sharedPreferences:SharedPreferences
    private var fullName:String? = null
    private var mobileNumber:String? = null
    private var email:String? = null
    private var userId :String?= null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater,R.layout.profile_edit_fragment,container,false)
        binding.profileViewModel = viewModel
        sharedPreferences = (activity as AppCompatActivity).getSharedPreferences(resources.getString(R.string.registration_details_sharedPreferences),Context.MODE_PRIVATE)
        fullName = sharedPreferences.getString("fullName", "")
        mobileNumber = sharedPreferences.getString("mobileNumber","")
        email = sharedPreferences.getString("email","")
        userId = sharedPreferences.getString("userId","")
        setUpUiData()
        subUpdateProfile()
        val view:View = binding.root
        viewModel.errorResult.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })


      /*  binding.tvEdit.setOnClickListener {
            binding.edtFullName.requestFocus()
            binding.edtEmail.requestFocus()
            Toast.makeText(context,"Enable the EditText",Toast.LENGTH_LONG).show()
        }*/

        return view
    }

    fun subUpdateProfile(){
        binding.buttonUpdateProfile.setOnClickListener {
            viewModel.editProfileObservable().observe(viewLifecycleOwner, Observer<ProfileEditResponseModel> {
                Toast.makeText(context,it.message,Toast.LENGTH_LONG).show()
                binding.edtFullName.setText(it.userProfileUpdateData.fullName)
             binding.edtEmail.setText(it.userProfileUpdateData.email)
            })
            viewModel.ApiCallEditProfile()
        }
    }

    fun setUpUiData(){
       viewModel.fullname = fullName
       viewModel.email = email
       viewModel.mobileNumber =mobileNumber
       viewModel.userId = userId
    }//This function is used to set the SharedPreference data to the UI


}