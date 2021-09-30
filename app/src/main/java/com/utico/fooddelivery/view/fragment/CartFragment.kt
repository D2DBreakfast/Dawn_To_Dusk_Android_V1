package com.utico.fooddelivery.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.utico.fooddelivery.adapter.AdapterCart
import com.utico.fooddelivery.databinding.FragmentCartBinding
import com.utico.fooddelivery.model.FoodList
import com.utico.fooddelivery.view.AddFragmentToActivity
import com.utico.fooddelivery.viewmodel.SearchViewModel

class CartFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel
    private var _binding: FragmentCartBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var adapterGlobalSearch: AdapterCart

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        _binding = FragmentCartBinding.inflate(inflater, container, false)

        val root: View = binding.root

       /* val textView: TextView = binding.textDashboard*/
       /* dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
           *//* textView.text = it*//*
        })*/

        setupAdapter()
        initViewModel()
        setButtonClickEvent()
        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setupAdapter(){
       val globalSearchRecyclerView = binding.globleSearchRecyclerview
           globalSearchRecyclerView.layoutManager = LinearLayoutManager(activity)
        /*val decoration = DividerItemDecoration(activity,DividerItemDecoration.VERTICAL)
        globalSearchRecyclerView.addItemDecoration(decoration)*/
        adapterGlobalSearch = AdapterCart()
        globalSearchRecyclerView.adapter = adapterGlobalSearch
    }

    fun initViewModel(){
       viewModel.getFoodSearchListObserable().observe(viewLifecycleOwner, Observer<FoodList> {
          adapterGlobalSearch.foodlist = it.data.toMutableList()
           adapterGlobalSearch.notifyDataSetChanged()
       })
        viewModel.getFoodSearchList()
    }

    /*Theis function is used to set all the button click event here*/
    fun setButtonClickEvent(){
        binding.tvCoupons.setOnClickListener {
          val intent = Intent(context,AddFragmentToActivity::class.java)
              intent.putExtra("FragmentName","CouponsFragment")
              startActivity(intent)
        }

        /*button click on order tracking */
        binding.fltBtnOrderTracking.setOnClickListener {
            val intent = Intent(context,AddFragmentToActivity::class.java)
                 intent.putExtra("FragmentName","OrderTrackingFragment")
                startActivity(intent)
        }
    }
}