package com.utico.fooddelivery.view.fragment

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.utico.fooddelivery.R
import com.utico.fooddelivery.databinding.AddAddressFragmentBinding
import com.utico.fooddelivery.databinding.ItemRowAddressBinding
import com.utico.fooddelivery.view.AddFragmentToActivity
import com.utico.fooddelivery.viewmodel.AddAddressViewModel

class AddAddressFragment : Fragment() {
   private lateinit var binding: AddAddressFragmentBinding

    companion object {
        fun newInstance() = AddAddressFragment()
    }

    private lateinit var viewModel: AddAddressViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddAddressFragmentBinding.inflate(inflater,container,false)
        val view : View = binding.root
        val btn_google_location =binding.btnGoogleLocation
            btn_google_location.setOnClickListener {
                val intent = Intent(context,AddFragmentToActivity::class.java)
                    intent.putExtra("FragmentName","GoogleLocationFragment")
                    startActivity(intent)
            }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddAddressViewModel::class.java)
        // TODO: Use the ViewModel
    }

}