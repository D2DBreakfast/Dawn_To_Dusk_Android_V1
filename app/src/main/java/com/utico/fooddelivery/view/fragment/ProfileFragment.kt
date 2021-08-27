package com.utico.fooddelivery.view.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.utico.fooddelivery.databinding.FragmentProfileBinding
import com.utico.fooddelivery.view.AddFragmentToActivity
import com.utico.fooddelivery.viewmodel.ProfileViewModel


class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding

     companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       // val view = inflater.inflate(R.layout.fragment_profile, container, false)
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        val view:View = binding.root

        val btnHelpSupport = binding.btnwHelpSupport
            btnHelpSupport.setOnClickListener {
                val intent = Intent(context,AddFragmentToActivity::class.java)
                intent.putExtra("FragmentName","HelpSupportFragment")
                startActivity(intent)
            }

        val btnPaymentMode = binding.btnPaymentMode
            btnPaymentMode.setOnClickListener {
               val intent = Intent(context,AddFragmentToActivity::class.java)
                   intent.putExtra("FragmentName","PaymentModeFragment")
                startActivity(intent)
            }

        return view
    }


}


