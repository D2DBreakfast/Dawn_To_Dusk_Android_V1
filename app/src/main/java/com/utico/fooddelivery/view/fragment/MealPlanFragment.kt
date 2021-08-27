package com.utico.fooddelivery.view.fragment

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.utico.fooddelivery.R
import com.utico.fooddelivery.adapter.AdapterMealPlan
import com.utico.fooddelivery.databinding.FragmentMealPlanBinding
import com.utico.fooddelivery.viewmodel.MealPlanViewModel



class MealPlanFragment : Fragment() {
    private lateinit var viewModel: MealPlanViewModel
    private var _binding: FragmentMealPlanBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapterMealPlan: AdapterMealPlan

    companion object {
        val TAG = MealPlanFragment::class.java.simpleName
        @JvmStatic
        fun newInstance() = MealPlanFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
         val view = inflater.inflate(R.layout.fragment_meal_plan, container, false)
          viewModel = ViewModelProvider(this).get(MealPlanViewModel::class.java)
          _binding = FragmentMealPlanBinding.inflate(inflater,container,false)
        initview(view)
         return view


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MealPlanViewModel::class.java)

    }

    fun initview(view: View){
       val recyclerView = binding.mealplanRecyclreview
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val decoration  = DividerItemDecoration(activity,DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)
        adapterMealPlan = AdapterMealPlan()
        recyclerView.adapter = adapterMealPlan

    }


}