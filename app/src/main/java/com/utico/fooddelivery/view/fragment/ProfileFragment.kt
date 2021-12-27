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
import androidx.lifecycle.ViewModelProvider
import com.utico.fooddelivery.R
import com.utico.fooddelivery.databinding.FragmentProfileBinding
import com.utico.fooddelivery.view.AddFragmentToActivity
import com.utico.fooddelivery.view.RegistrationActivity
import com.utico.fooddelivery.viewmodel.ProfileViewModel


class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding

     companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel
    private lateinit var sharedPreferences: SharedPreferences
    private var fullName:String? = null
    private var mobileNumber:String? = null
    private var email:String? = null
    private var userId :String?= null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        sharedPreferences = (activity as AppCompatActivity).getSharedPreferences(resources.getString(R.string.registration_details_sharedPreferences),Context.MODE_PRIVATE)
        fullName = sharedPreferences.getString("fullName","")
        mobileNumber = sharedPreferences.getString("mobileNumber","")
        email = sharedPreferences.getString("email","")
        userId = sharedPreferences.getString("userId","")
        setDataToUI()
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


        toCheckUserRegisterOrNot(userId)
        return view
    }

    fun setDataToUI(){
        binding.tvName.setText(fullName)
        binding.tvMobileNumber.setText(mobileNumber)
        binding.tvEmail.setText(email)
    }

    fun toCheckUserRegisterOrNot(userID: String?) {
        if (userID.equals("")||userID.equals(null)) {
            Toast.makeText(context,"Before Add to Cart Please Register!!",Toast.LENGTH_LONG).show()
            val intent = Intent(context, RegistrationActivity::class.java)
            context?.startActivity(intent)
        }
    }
}
/*
https://stackoverflow.com/questions/52918895/retrieving-value-using-shared-preference-in-kotlin
*/


