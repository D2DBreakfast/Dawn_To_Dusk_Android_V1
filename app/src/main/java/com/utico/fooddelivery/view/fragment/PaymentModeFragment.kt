package com.utico.fooddelivery.view.fragment

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.utico.fooddelivery.R
import com.utico.fooddelivery.databinding.FragmentProfileBinding
import com.utico.fooddelivery.databinding.PaymentModeFragmentBinding
import com.utico.fooddelivery.view.AddFragmentToActivity
import com.utico.fooddelivery.viewmodel.PaymentModeViewModel

class PaymentModeFragment : Fragment() {
    lateinit var binding: PaymentModeFragmentBinding

    companion object {
        fun newInstance() = PaymentModeFragment()
    }

    private lateinit var viewModel: PaymentModeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PaymentModeFragmentBinding.inflate(inflater,container,false)
        val view:View = binding.root

        /*Click event for the Cards deatails*/
        val btncard = binding.btnCard
        btncard.setOnClickListener {
            val intent = Intent(context,AddFragmentToActivity::class.java)
            intent.putExtra("FragmentName","CardPaymentFragment")
            startActivity(intent)
        }

        /*Click event for the Bank details*/
        val btnBank = binding.btnBank
            btnBank.setOnClickListener {
                val intent = Intent(context,AddFragmentToActivity::class.java)
                intent.putExtra("FragmentName","BankDetailsFragment")
                startActivity(intent)
            }


        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PaymentModeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}