package com.utico.fooddelivery.view.fragment

import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import com.utico.fooddelivery.adapter.AdapterFoodCategory
import com.utico.fooddelivery.adapter.AdapterFoodOrderShortDesc
import com.utico.fooddelivery.databinding.FragmentHomeBinding
import com.utico.fooddelivery.view.DashBoardActivity
import com.utico.fooddelivery.viewmodel.HomeViewModel

class HomeFragment : Fragment(){

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
/*
    private lateinit var AdapterFoodOrderShortDesc : AdapterFoodOrderShortDesc()
*/

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.homeViewModel = homeViewModel
        val view: View = binding.root
        initViewmodel(view)

        val btnMeal = binding.btnMeals
        btnMeal.setOnClickListener {
            Toast.makeText(context, "button is clicked", Toast.LENGTH_SHORT).show()
            val frag = MealPlanFragment.newInstance()
            (activity as DashBoardActivity).replaceFragment(frag,MealPlanFragment.TAG)
        }
        return view
    }

    companion object {
        val TAG = HomeFragment::class.java.simpleName
        @JvmStatic
        fun newInstance() = HomeFragment()
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

  fun  initViewmodel(view:View) {

      val categoryNameRecyclerview = binding.foodCategoryRecyclerview
           categoryNameRecyclerview.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
           categoryNameRecyclerview.adapter = AdapterFoodCategory()

      val foodShortxDescrecyclerview = binding.foodDetailsRecyclerview
          foodShortxDescrecyclerview.layoutManager = LinearLayoutManager(activity)
          val decoration  = DividerItemDecoration(activity,DividerItemDecoration.VERTICAL)
          foodShortxDescrecyclerview.addItemDecoration(decoration)
          foodShortxDescrecyclerview.adapter = AdapterFoodOrderShortDesc()

  }


}



