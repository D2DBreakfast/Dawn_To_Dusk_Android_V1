package com.utico.fooddelivery.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.utico.fooddelivery.R
import com.utico.fooddelivery.adapter.AdapterCoupons
import com.utico.fooddelivery.databinding.CouponsFragmentBinding
import com.utico.fooddelivery.databinding.ProfileEditFragmentBinding

class CouponsFragment : Fragment() {

    companion object {
        fun newInstance() = CouponsFragment()
    }

    private lateinit var viewModel: CouponsViewModel
    private lateinit var binding: CouponsFragmentBinding
    private lateinit var adapterCoupons: AdapterCoupons

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CouponsFragmentBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this).get(CouponsViewModel::class.java)
        val view:View = binding.root

        initAdapter()
        return view

    }

 fun initAdapter(){
     val recyclerView = binding.recyclerView
         recyclerView.layoutManager = LinearLayoutManager(activity)
         adapterCoupons = AdapterCoupons()
         recyclerView.adapter = adapterCoupons
 }

}