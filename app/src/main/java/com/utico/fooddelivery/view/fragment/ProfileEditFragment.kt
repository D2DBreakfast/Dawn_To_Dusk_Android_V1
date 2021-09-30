package com.utico.fooddelivery.view.fragment

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import com.utico.fooddelivery.R
import com.utico.fooddelivery.`interface`.ProfileListener
import com.utico.fooddelivery.databinding.ProfileEditFragmentBinding
import com.utico.fooddelivery.model.EditProfileResponse
import com.utico.fooddelivery.model.ProfileFakeApi
import com.utico.fooddelivery.model.profile
import com.utico.fooddelivery.viewmodel.ProfileViewModel

class ProfileEditFragment : Fragment(),ProfileListener {

    companion object {
        fun newInstance() = ProfileEditFragment()
    }

    private lateinit var viewModel: ProfileViewModel
    private lateinit var binding: ProfileEditFragmentBinding
    private lateinit var profile: profile
    var profildata = mutableListOf<profile>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater,R.layout.profile_edit_fragment,container,false)
        viewModel.profileListener = this
        binding.profileViewModel = viewModel
        val view:View = binding.root

        viewModel.errorResult.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })


        binding.tvEdit.setOnClickListener {
            binding.edtTextinputlayoutName.requestFocus()
            binding.editTextinputlayoutEmail.requestFocus()
            Toast.makeText(context,"Enable the EditText",Toast.LENGTH_LONG).show()
        }

        setUpData()
        editButtonenable()
        return view
    }


    fun setUpData(){
        viewModel.getProfileObserable().observe(viewLifecycleOwner, Observer<ProfileFakeApi> {
            if (it==null){
            }else {
                binding.edtTextinputlayoutName.setText(it.data.first_name)
                binding.editTextinputlayoutEmail.setText(it.data.email)
                binding.editTextinputlayoutMobile.setText((it.data.id).toString())
                val imageUrl = it.data.avatar
                Picasso.get()
                    .load(imageUrl)
                    .into(binding.editProfileImageview)

            }
        })
        viewModel.ApiCallProfile()

    }

    /*Edit button click event*/
    fun editButtonenable(){

    }

    override fun editProfile(editProfileResponse: LiveData<String>) {
       editProfileResponse.observe(viewLifecycleOwner, Observer {
           Toast.makeText(context,it,Toast.LENGTH_LONG).show()
       })
    }


}