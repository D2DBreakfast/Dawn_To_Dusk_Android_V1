package com.utico.fooddelivery.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.utico.fooddelivery.databinding.AddCardBankFragmentBinding
import com.utico.fooddelivery.viewmodel.AddCardBankViewModel

class AddCardBankFragment : Fragment() {
    private var binding:AddCardBankFragmentBinding? = null

    companion object {
        fun newInstance() = AddCardBankFragment()
    }

    private lateinit var viewModel: AddCardBankViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = AddCardBankFragmentBinding.inflate(inflater,container,false)
        val view : View = binding!!.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddCardBankViewModel::class.java)
    }

}