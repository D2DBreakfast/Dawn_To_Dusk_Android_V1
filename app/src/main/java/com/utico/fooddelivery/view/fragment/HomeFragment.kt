package com.utico.fooddelivery.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.utico.fooddelivery.R
import com.utico.fooddelivery.adapter.AdapterFoodCategory
import com.utico.fooddelivery.adapter.AdapterFoodOrderShortDesc
import com.utico.fooddelivery.databinding.FragmentHomeBinding
import com.utico.fooddelivery.model.FoodShortDescList
import com.utico.fooddelivery.view.AddFragmentToActivity
import com.utico.fooddelivery.viewmodel.HomeViewModel

class HomeFragment : Fragment(){

    private lateinit var viewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    lateinit var adapterFoodOrderShortDesc: AdapterFoodOrderShortDesc
/*
    private lateinit var AdapterFoodOrderShortDesc : AdapterFoodOrderShortDesc()
*/

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.homeViewModel = viewModel
        setHasOptionsMenu(true)
        val view: View = binding.root


        val btnMeal = binding.btnMealPlan
        btnMeal.setOnClickListener {
            /*val frag = MealPlanFragment.newInstance()
            (activity as DashBoardActivity).replaceFragment(frag,MealPlanFragment.TAG)*/
            /*val activity = context as AppCompatActivity
            val mealPlanFragment = MealPlanFragment()
            activity.supportFragmentManager.beginTransaction().replace(R.id.dashBoardLayout,mealPlanFragment).addToBackStack(null).commit()
            */
            val intent = Intent (context, AddFragmentToActivity::class.java)
             intent.putExtra("FragmentName","MealPlanFragment")
             startActivity(intent)
        }

        initViewmodel(view)
        initViewModel()
        return view
    }

    companion object {
        /*val TAG = HomeFragment::class.java.simpleName
        @JvmStatic*/
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
           adapterFoodOrderShortDesc = AdapterFoodOrderShortDesc()
          foodShortxDescrecyclerview.adapter = adapterFoodOrderShortDesc

  }


  fun initViewModel(){
      viewModel.getyFoodShortDescListObserable().observe(viewLifecycleOwner, Observer<FoodShortDescList> {
        adapterFoodOrderShortDesc.foodDescList = it.data.toMutableList()
        adapterFoodOrderShortDesc.notifyDataSetChanged()
      })
      viewModel.makeApiCallFoodDesc()
  }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu,menu)
        val searchItem = menu.findItem(R.id.app_bar_search)
       /* if (searchItem!=null){
            //val searchView = searchItem.actionView as SearchView
        }*/
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.app_bar_search ->Toast.makeText(context,"Click On Search",Toast.LENGTH_LONG).show()
        }
        return super.onOptionsItemSelected(item)
    }
}



