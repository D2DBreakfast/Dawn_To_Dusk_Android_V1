package com.utico.fooddelivery.view.fragment

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.utico.fooddelivery.R
import com.utico.fooddelivery.adapter.AdapterAddress
import com.utico.fooddelivery.databinding.AddCardBankFragmentBinding
import com.utico.fooddelivery.databinding.AddressFragmentBinding
import com.utico.fooddelivery.view.AddFragmentToActivity
import com.utico.fooddelivery.viewmodel.AddressViewModel

class AddressFragment : Fragment() {
   private lateinit var binding: AddressFragmentBinding
   private lateinit var viewModel: AddressViewModel


    companion object {
        fun newInstance() = AddressFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddressFragmentBinding.inflate(inflater,container,false)
        val view : View = binding.root
        setUpAdapter()
         /*Click event for the adding new address*/
        val btnAddress =  binding.floatingActionButtonAddAddress
            btnAddress.setOnClickListener {
                val intent = Intent(context,AddFragmentToActivity::class.java)
                intent.putExtra("FragmentName","AddAddressFragment")
                startActivity(intent)
            }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddressViewModel::class.java)
        // TODO: Use the ViewModel
    }

    fun setUpAdapter(){
        var recyclerView = binding.addressRecyclerview
            recyclerView.layoutManager = LinearLayoutManager(activity)
            recyclerView.adapter = AdapterAddress()
    }

}