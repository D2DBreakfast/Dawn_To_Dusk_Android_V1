package com.utico.fooddelivery.view.fragment

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.utico.fooddelivery.R
import com.utico.fooddelivery.`interface`.FoodSubCategoryListener
import com.utico.fooddelivery.adapter.AdapterFoodSubCategory
import com.utico.fooddelivery.adapter.AdapterFoodOrderShortDesc
import com.utico.fooddelivery.databinding.FragmentHomeBinding
import com.utico.fooddelivery.model.FoodShortDescList
import com.utico.fooddelivery.model.FoodSubCategoryList
import com.utico.fooddelivery.view.LoginActivity
import com.utico.fooddelivery.viewmodel.HomeViewModel


class HomeFragment : Fragment(),FoodSubCategoryListener{

    private lateinit var viewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    lateinit var adapterFoodOrderShortDesc: AdapterFoodOrderShortDesc
    lateinit var adapterFoodSubCategory: AdapterFoodSubCategory
    var searchtext:String? = null
    private lateinit var btnAalCart:Button
    private lateinit var btnMealPlan:Button
    private lateinit var  btnLogout:ImageView
/*
    private lateinit var AdapterFoodOrderShortDesc : AdapterFoodOrderShortDesc()
*/

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.homeViewModel = viewModel
        btnAalCart = binding.btnAAlCart
        btnMealPlan = binding.btnMealPlan
        btnLogout = binding.logoutImageview
        /*setHasOptionsMenu(true)*/
        val view: View = binding.root

        val toolbar = binding.homeCustomToolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        /* (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
         (activity as AppCompatActivity?)!!.supportActionBar!!.setCustomView(R.layout.)*/




      /*  val btnMeal = binding.btnMealPlan
        btnMeal.setOnClickListener {
            *//*val frag = MealPlanFragment.newInstance()
            (activity as DashBoardActivity).replaceFragment(frag,MealPlanFragment.TAG)*//*
            *//*val activity = context as AppCompatActivity
            val mealPlanFragment = MealPlanFragment()
            activity.supportFragmentManager.beginTransaction().replace(R.id.dashBoardLayout,mealPlanFragment).addToBackStack(null).commit()
            *//*
            val intent = Intent (context, AddFragmentToActivity::class.java)
             intent.putExtra("FragmentName","MealPlanFragment")
             startActivity(intent)
        }*/

           btnLogout.setOnClickListener {
               val intent = Intent(context,LoginActivity::class.java)
                   startActivity(intent)
                   (activity as AppCompatActivity).finish()
           }

        initView()
        initViewModel()
        searchFood()
        buttonAalCart()
        buttonmealPlans()
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

  fun  initView() {

      val categoryNameRecyclerview = binding.foodCategoryRecyclerview
           categoryNameRecyclerview.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
           adapterFoodSubCategory = AdapterFoodSubCategory(this)
           categoryNameRecyclerview.adapter = adapterFoodSubCategory

      val A_al_cart_recyclerview = binding.aLaCartRecyclerview
      A_al_cart_recyclerview.layoutManager = LinearLayoutManager(activity)
         /* val decoration  = DividerItemDecoration(activity,DividerItemDecoration.VERTICAL)
          foodShortxDescrecyclerview.addItemDecoration(decoration)*/
           adapterFoodOrderShortDesc = AdapterFoodOrderShortDesc()
      A_al_cart_recyclerview.adapter = adapterFoodOrderShortDesc

  }

  fun initViewModel(){

      viewModel.getyFoodShortDescListObserable().observe(viewLifecycleOwner, Observer<FoodShortDescList> {
        adapterFoodOrderShortDesc.foodDescList = it.data.toMutableList()
        adapterFoodOrderShortDesc.notifyDataSetChanged()
      })
      viewModel.makeApiCallFoodDesc()

      /*call the viewModel for Food Sub Category*/

      viewModel.getFoodSubCategoryObserable().observe(viewLifecycleOwner, Observer<FoodSubCategoryList> {
        adapterFoodSubCategory.foodSubCategoryList = it.data.toMutableList()
        adapterFoodSubCategory.notifyDataSetChanged()
      })
      viewModel.APICallFoodSubCategory()
  }



    override fun getFoodSubCategoryName(foodCategoryName: String) {
        Toast.makeText(context,foodCategoryName,Toast.LENGTH_LONG).show()
        viewModel.getFoodCategoryName("1")
        viewModel.getFoodSubCategoryWiseObservable().observe(viewLifecycleOwner, Observer<FoodShortDescList> {
           adapterFoodOrderShortDesc.foodDescList.clear()
           adapterFoodOrderShortDesc.foodDescList = it.data.toMutableList()
           adapterFoodOrderShortDesc.notifyDataSetChanged()
        })
        viewModel.MakeApiCallFoodSubCategoryWiseData()
    }

       fun searchFood(){
           val searchText = binding.searchHere
               searchText.doOnTextChanged { text, start, before, count ->
                   searchtext = text!!.toString()

                   if (text!!.length > 1){
                    viewModel.getFoodSearchObservable().observe(viewLifecycleOwner, Observer<FoodShortDescList> {
                    adapterFoodOrderShortDesc.foodDescList = it.data.toMutableList()
                    adapterFoodOrderShortDesc.notifyDataSetChanged()
                    })
                    viewModel.APICallSearchFood(searchtext.toString())
                   }
                   else if (text!!.length < 0){
                       initView()

                   }
               }
       }

    fun buttonmealPlans(){
        val A_al_cart_recyclerview = binding.aLaCartRecyclerview
        val meal_plan_recyclerview = binding.mealPlansRecyclerview
        val vegSwitch = binding.vegSwitch
        val foodCategoryRecyclerView = binding.foodCategoryRecyclerview


        btnMealPlan.setOnClickListener {
                A_al_cart_recyclerview.visibility = View.VISIBLE
                meal_plan_recyclerview.visibility = View.VISIBLE
                vegSwitch.visibility = View.GONE
                foodCategoryRecyclerView.visibility = View.GONE
                btnMealPlan.setBackgroundColor(resources.getColor(R.color.green))
                btnMealPlan.setTextColor(resources.getColor(R.color.white))
                btnAalCart.setBackgroundColor(resources.getColor(R.color.white))
                btnAalCart.setTextColor(resources.getColor(R.color.black))

                viewModel.getFoodSearchObservable().observe(viewLifecycleOwner, Observer<FoodShortDescList> {
                    adapterFoodOrderShortDesc.foodDescList = it.data.toMutableList()
                    adapterFoodOrderShortDesc.notifyDataSetChanged()
                })
                viewModel.APICallSearchFood("1")

            }

    }

    fun buttonAalCart(){
        val A_al_cart_recyclerview = binding.aLaCartRecyclerview
        val meal_plan_recyclerview = binding.mealPlansRecyclerview
        val vegSwitch = binding.vegSwitch
        val foodCategoryRecyclerView = binding.foodCategoryRecyclerview

        btnAalCart.setOnClickListener {
            A_al_cart_recyclerview.visibility = View.VISIBLE
            meal_plan_recyclerview.visibility =View.GONE
            vegSwitch.visibility = View.VISIBLE
            foodCategoryRecyclerView.visibility = View.VISIBLE
            btnAalCart.setBackgroundColor(resources.getColor(R.color.green))
            btnAalCart.setTextColor(resources.getColor(R.color.white))
            btnMealPlan.setBackgroundColor(resources.getColor(R.color.white))
            btnMealPlan.setTextColor(resources.getColor(R.color.black))

            viewModel.getFoodSearchObservable().observe(viewLifecycleOwner, Observer<FoodShortDescList> {
                adapterFoodOrderShortDesc.foodDescList = it.data.toMutableList()
                adapterFoodOrderShortDesc.notifyDataSetChanged()
            })
            viewModel.APICallSearchFood("2")

        }

    }


/*
   override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu,menu)
        val searchItem = menu.findItem(R.id.app_bar_search)
       *//* if (searchItem!=null){
            //val searchView = searchItem.actionView as SearchView
        }*//*
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.app_bar_search ->Toast.makeText(context,"Click On Search",Toast.LENGTH_LONG).show()
        }
        return super.onOptionsItemSelected(item)
    }*/

}


/*
https://abhiandroid.com/ui/calendarview
*/





