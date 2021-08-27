package com.utico.fooddelivery.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.utico.fooddelivery.R
import com.utico.fooddelivery.adapter.AdapterMealPlan
import com.utico.fooddelivery.databinding.FragmentMealPlanBinding
import com.utico.fooddelivery.model.MealPlanList
import com.utico.fooddelivery.viewmodel.MealPlanViewModel
import android.view.MenuInflater






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
          viewModel = ViewModelProvider(this).get(MealPlanViewModel::class.java)
          _binding = FragmentMealPlanBinding.inflate(inflater,container,false)
          setHasOptionsMenu(true);

        val view:View = binding.root
          initview()
          initViewModel()
         return view


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MealPlanViewModel::class.java)

    }

    fun initview(){
       val recyclerView = binding.mealplanRecyclreview
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val decoration  = DividerItemDecoration(activity,DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)
        adapterMealPlan = AdapterMealPlan()
        recyclerView.adapter = adapterMealPlan

    }

   fun initViewModel(){
      viewModel.getMealPlanListObserable().observe(viewLifecycleOwner, Observer<MealPlanList> {
      adapterMealPlan.mealPlanList = it.data.toMutableList()
      adapterMealPlan.notifyDataSetChanged()
      })
       viewModel.makeApiCallGetMealPlan()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu,menu)
        val searchItem = menu.findItem(R.id.app_bar_search)
        /*if (searchItem!=null){
            val searchView = searchItem.actionView as SearchView
        }*/
        super.onCreateOptionsMenu(menu, inflater)
    }
}