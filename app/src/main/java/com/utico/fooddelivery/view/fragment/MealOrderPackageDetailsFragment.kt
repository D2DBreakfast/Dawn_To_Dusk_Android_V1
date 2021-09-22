package com.utico.fooddelivery.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.utico.fooddelivery.adapter.AdapterUpcomingMeals
import com.utico.fooddelivery.databinding.MealOrderPackageDetailsFragmentBinding
import com.utico.fooddelivery.viewmodel.MealOrderDetailskViewModel

class MealOrderPackageDetailsFragment : Fragment() {
    private lateinit var binding: MealOrderPackageDetailsFragmentBinding
    private lateinit var adapterUpcomingMeals:AdapterUpcomingMeals

    companion object {
        fun newInstance() = MealOrderPackageDetailsFragment()
    }

    private lateinit var viewModel: MealOrderDetailskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MealOrderPackageDetailsFragmentBinding.inflate(inflater,container,false)
        val view:View=binding.root
        setUpAdapter()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MealOrderDetailskViewModel::class.java)
        // TODO: Use the ViewModel
    }

      fun setUpAdapter(){
         val recyclerView = binding.upcomingMealsRecyclerView
             recyclerView.layoutManager = LinearLayoutManager(activity)
             adapterUpcomingMeals = AdapterUpcomingMeals()
             recyclerView.adapter =adapterUpcomingMeals
      }
}