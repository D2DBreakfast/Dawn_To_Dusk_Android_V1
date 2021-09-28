package com.utico.fooddelivery.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import com.utico.fooddelivery.R
import com.utico.fooddelivery.databinding.ProfileEditFragmentBinding
import com.utico.fooddelivery.model.ProfileFakeApi
import com.utico.fooddelivery.model.profile
import com.utico.fooddelivery.viewmodel.ProfileEditViewModel

class ProfileEditFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileEditFragment()
    }

    private lateinit var viewModel: ProfileEditViewModel
    private lateinit var binding: ProfileEditFragmentBinding
    private lateinit var profile: profile
    var profildata = mutableListOf<profile>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ProfileEditViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater,R.layout.profile_edit_fragment,container,false)
        binding.profileViewModel = viewModel
        val view:View = binding.root
        setUpData()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileEditViewModel::class.java)
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

}