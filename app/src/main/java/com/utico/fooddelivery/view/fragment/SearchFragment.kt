package com.utico.fooddelivery.view.fragment

import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.utico.fooddelivery.adapter.AdapterGlobalSearch
import com.utico.fooddelivery.databinding.FragmentSearchBinding
import com.utico.fooddelivery.model.FoodList
import com.utico.fooddelivery.viewmodel.SearchViewModel

class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel
    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var adapterGlobalSearch: AdapterGlobalSearch

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        val root: View = binding.root

       /* val textView: TextView = binding.textDashboard*/
       /* dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
           *//* textView.text = it*//*
        })*/

        setupAdapter()
        initViewModel()
        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setupAdapter(){
       val globalSearchRecyclerView = binding.globleSearchRecyclerview
           globalSearchRecyclerView.layoutManager = LinearLayoutManager(activity)
        val decoration = DividerItemDecoration(activity,DividerItemDecoration.VERTICAL)
        globalSearchRecyclerView.addItemDecoration(decoration)
        adapterGlobalSearch = AdapterGlobalSearch()
        globalSearchRecyclerView.adapter = adapterGlobalSearch
    }

    fun initViewModel(){
       viewModel.getFoodSearchListObserable().observe(viewLifecycleOwner, Observer<FoodList> {
          adapterGlobalSearch.foodlist = it.data.toMutableList()
           adapterGlobalSearch.notifyDataSetChanged()
       })
        viewModel.getFoodSearchList()
    }
}