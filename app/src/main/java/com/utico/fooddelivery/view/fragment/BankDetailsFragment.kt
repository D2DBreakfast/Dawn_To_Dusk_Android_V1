package com.utico.fooddelivery.view.fragment

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.utico.fooddelivery.R
import com.utico.fooddelivery.adapter.AdapterBankDetails
import com.utico.fooddelivery.databinding.BankDetailsFragmentBinding
import com.utico.fooddelivery.view.AddFragmentToActivity
import com.utico.fooddelivery.viewmodel.BankDetailsViewModel

class BankDetailsFragment : Fragment() {
    private var binding: BankDetailsFragmentBinding? = null

    companion object {
        fun newInstance() = BankDetailsFragment()
    }

    private lateinit var viewModel: BankDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //val view = inflater.inflate(R.layout.bank_details_fragment, container, false)
        binding = BankDetailsFragmentBinding.inflate(inflater,container,false)
        val view:View = binding!!.root
        initAdapter()
        initView()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BankDetailsViewModel::class.java)
    }

    fun initAdapter(){
        val recyclerview = binding!!.recyclerView
            recyclerview.layoutManager = LinearLayoutManager(activity)
        /*val decoration = DividerItemDecoration(activity,DividerItemDecoration.VERTICAL)
            recyclerview.addItemDecoration(decoration)*/
            recyclerview.adapter = AdapterBankDetails()


    }


    fun initView(){

        val floatingButton_add_card_bank = binding!!.floatingActionButtonAddBankDetails
            floatingButton_add_card_bank.setOnClickListener {
              val intent = Intent(context,AddFragmentToActivity::class.java)
                  intent.putExtra("FragmentName","AddCardBankFragment")
                  startActivity(intent)
            }
    }

}