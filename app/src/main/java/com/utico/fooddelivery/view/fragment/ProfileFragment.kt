package com.utico.fooddelivery.view.fragment


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.utico.fooddelivery.R
import com.utico.fooddelivery.databinding.FragmentProfileBinding
import com.utico.fooddelivery.model.Data
import com.utico.fooddelivery.model.ProfileFakeApi
import com.utico.fooddelivery.model.profile
import com.utico.fooddelivery.view.AddFragmentToActivity
import com.utico.fooddelivery.viewmodel.ProfileViewModel


class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding

     companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel
    private lateinit var sharedPreferences: SharedPreferences
    private var name:String? = null
    private var mobileNumber:String? = null
    private var email:String? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        sharedPreferences = (activity as AppCompatActivity).getSharedPreferences(resources.getString(R.string.app_name),Context.MODE_PRIVATE)
        name = sharedPreferences.getString("name","")
        mobileNumber = sharedPreferences.getString("mobileNumber","")
        email = sharedPreferences.getString("til_email","")

        val view:View = binding.root


        val btnEditProfile = binding.profileCardView
            btnEditProfile.setOnClickListener {
                val intent = Intent(context,AddFragmentToActivity::class.java)
                    intent.putExtra("FragmentName","ProfileEditFragment")
                    startActivity(intent)
            }


        val btnHelpSupport = binding.lnrHelpSupport
            btnHelpSupport.setOnClickListener {
                val intent = Intent(context,AddFragmentToActivity::class.java)
                intent.putExtra("FragmentName","HelpSupportFragment")
                startActivity(intent)
            }

        val btnPaymentMode = binding.lnrPaymentMode
            btnPaymentMode.setOnClickListener {
               val intent = Intent(context,AddFragmentToActivity::class.java)
                   intent.putExtra("FragmentName","PaymentModeFragment")
                startActivity(intent)
            }

        /*Set Click event for Address Button*/
        val btnAddress = binding.lnrAddress
            btnAddress.setOnClickListener {
                val intent = Intent(context,AddFragmentToActivity::class.java)
                    intent.putExtra("FragmentName","AddressFragment")
                startActivity(intent)
            }

        /*Set Click event for the Order History*/
        val btn_history = binding.lnrOrderHistory
            btn_history.setOnClickListener {
              val intent = Intent(context,AddFragmentToActivity::class.java)
                  intent.putExtra("FragmentName","OrderHistoryFragment")
                 startActivity(intent)
            }

        /*Set Click event for the Order History*/
        val btn_AppSettings = binding.lnrAppSettings
        btn_AppSettings.setOnClickListener {

        }

         initUIData()

        return view
    }

    fun initUIData(){
       viewModel.getProfileObserable().observe(viewLifecycleOwner, Observer<ProfileFakeApi> {
           if (it == null){
               Toast.makeText(context,"Data Not Found",Toast.LENGTH_LONG).show()
           }else{
               binding.tvName.setText(name)
               binding.tvMobileNumber.setText(mobileNumber)
               binding.tvEmail.setText(email)
               binding.tvAddress.setText("Karnataka Bengaluru")
               Picasso.get()
                   .load(it.data.avatar)
                   .into(binding.profileImageView)
           }
       })
        viewModel.ApiCallProfile()
    }


}
/*
https://stackoverflow.com/questions/52918895/retrieving-value-using-shared-preference-in-kotlin
*/


