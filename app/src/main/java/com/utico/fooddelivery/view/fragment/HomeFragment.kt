package com.utico.fooddelivery.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.utico.fooddelivery.R
import com.utico.fooddelivery.`interface`.FoodSubCategoryListener
import com.utico.fooddelivery.adapter.AdapterCart
import com.utico.fooddelivery.adapter.AdapterBreakfastSubCategory
import com.utico.fooddelivery.adapter.AdapterBrunchSubCategory
import com.utico.fooddelivery.adapter.AdapterFoodOrderShortDesc
import com.utico.fooddelivery.databinding.FragmentHomeBinding
import com.utico.fooddelivery.model.MenuDetailsResponseModel
import com.utico.fooddelivery.model.MenuResponse
import com.utico.fooddelivery.model.SubCategoryResponseModel
import com.utico.fooddelivery.view.LoginActivity
import com.utico.fooddelivery.viewmodel.HomeViewModel


class HomeFragment : Fragment(),FoodSubCategoryListener{

    private lateinit var viewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    lateinit var adapterFoodOrderShortDesc: AdapterFoodOrderShortDesc
    lateinit var adapterBreakFastSubCategory: AdapterBreakfastSubCategory
    lateinit var adapterBrunchSubCategory: AdapterBrunchSubCategory
    lateinit var adapterCart: AdapterCart
    var searchtext:String? = null
    private lateinit var btnBreakfast:Button
    private lateinit var btnBrunch:Button
    private lateinit var  btnLogout:ImageView

    var itemMainCategoryName:String? = "Breakfast"
    var itemSubCategoryName:String? = "All Day Breakfast"
    var itemFoodType:String? = "Veg"
/*
    private lateinit var AdapterFoodOrderShortDesc : AdapterFoodOrderShortDesc()
*/

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.homeViewModel = viewModel
        btnBreakfast = binding.btnBreakfast
        btnBrunch = binding.btnBrunch
        btnLogout = binding.logoutImageview
        /*setHasOptionsMenu(true)*/
        val view: View = binding.root

        val toolbar = binding.homeCustomToolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        /* (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
         (activity as AppCompatActivity?)!!.supportActionBar!!.setCustomView(R.layout.)*/




      /*  val btnMeal = binding.btnBrunch
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

        initAdapter()
        initViewModel()
       // searchFood()
        buttonBreakfast()
        buttonmBrunch()
        getSubCategoryData()
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

  fun  initAdapter() {

      val breakfastSubCategoryRecyclerview = binding.breakfastSubCategoryRecyclerview
           breakfastSubCategoryRecyclerview.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
           adapterBreakFastSubCategory = AdapterBreakfastSubCategory(this)
           breakfastSubCategoryRecyclerview.adapter = adapterBreakFastSubCategory

      val brunchSubcategoryRecyclerView = binding.brunchSubCategoryRecyclerview
          brunchSubcategoryRecyclerView.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
          adapterBrunchSubCategory = AdapterBrunchSubCategory()
          brunchSubcategoryRecyclerView.adapter = adapterBrunchSubCategory

      val breakfastRecyclerview = binding.breakfastRecyclerview
      breakfastRecyclerview.layoutManager = LinearLayoutManager(activity)
         /* val decoration  = DividerItemDecoration(activity,DividerItemDecoration.VERTICAL)
          foodShortxDescrecyclerview.addItemDecoration(decoration)*/
           adapterFoodOrderShortDesc = AdapterFoodOrderShortDesc()
      breakfastRecyclerview.adapter = adapterFoodOrderShortDesc

      val brunchRecyclerView = binding.brunchRecyclerview
          brunchRecyclerView.layoutManager = LinearLayoutManager(activity)
          adapterCart = AdapterCart()
          brunchRecyclerView.adapter = adapterCart

  }

  fun initViewModel(){

      viewModel.getMenuDetailObservable().observe(viewLifecycleOwner, Observer<MenuDetailsResponseModel> {
          if (it.menuData==null){
            Toast.makeText(context,"Data not found"+ " "+it.toString(),Toast.LENGTH_SHORT).show()
          }else {
              adapterFoodOrderShortDesc.foodDescList = it.menuData.data.toMutableList()
              adapterFoodOrderShortDesc.notifyDataSetChanged()
          }
      })
      viewModel.ApiCallMenuDetails(itemMainCategoryName!!,itemSubCategoryName!!,itemFoodType!!)

  }



    override fun getFoodSubCategoryName(foodCategoryName: String) {
      /*  Toast.makeText(context,foodCategoryName,Toast.LENGTH_LONG).show()
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
                       initAdapter()

                   }
               }*/
       }



    fun buttonBreakfast(){
        val breakfastRecyclerview = binding.breakfastRecyclerview
        val brunchRecyclerview = binding.brunchRecyclerview
        val breakfastVegSwitch = binding.breakfastVegSwitch
        val brunchVegSwitch = binding.brunchVegSwitch
        val breakfastSubCategoryRecyclerView = binding.breakfastSubCategoryRecyclerview
        val brunchSubCategoryRecyclerview = binding.brunchSubCategoryRecyclerview
        val breakfastBannerImageView = binding.breakfastBannerImageView
        val brunchBannerImageView = binding.brunchBannerImageView

        btnBreakfast.setOnClickListener {
            breakfastRecyclerview.visibility = View.VISIBLE
            brunchRecyclerview.visibility = View.GONE
            breakfastVegSwitch.visibility = View.VISIBLE
            brunchVegSwitch.visibility = View.GONE
            breakfastSubCategoryRecyclerView.visibility = View.VISIBLE
            brunchSubCategoryRecyclerview.visibility = View.GONE
            breakfastBannerImageView.visibility = View.VISIBLE
            brunchBannerImageView.visibility = View.GONE

            btnBreakfast.setBackgroundColor(resources.getColor(R.color.green))
            btnBreakfast.setTextColor(resources.getColor(R.color.white))
            btnBrunch.setBackgroundColor(resources.getColor(R.color.white))
            btnBrunch.setTextColor(resources.getColor(R.color.black))


        }

    }
    fun buttonmBrunch(){
        val breakfastRecyclerview = binding.breakfastRecyclerview
        val brunchRecyclerview = binding.brunchRecyclerview
        val breakfastVegSwitch = binding.breakfastVegSwitch
        val brunchVegSwitch = binding.brunchVegSwitch
        val breakfastSubCategoryRecyclerView = binding.breakfastSubCategoryRecyclerview
        val brunchSubCategoryRecyclerview = binding.brunchSubCategoryRecyclerview
        val breakfastBannerImageView = binding.breakfastBannerImageView
        val brunchBannerImageView = binding.brunchBannerImageView



        btnBrunch.setOnClickListener {
            breakfastRecyclerview.visibility = View.GONE
            brunchRecyclerview.visibility = View.VISIBLE
            breakfastVegSwitch.visibility = View.GONE
            brunchVegSwitch.visibility = View.VISIBLE
            breakfastSubCategoryRecyclerView.visibility = View.GONE
            brunchSubCategoryRecyclerview.visibility = View.VISIBLE
            breakfastBannerImageView.visibility = View.GONE
            brunchBannerImageView.visibility =View.VISIBLE

            btnBrunch.setBackgroundColor(resources.getColor(R.color.green))
            btnBrunch.setTextColor(resources.getColor(R.color.white))
            btnBreakfast.setBackgroundColor(resources.getColor(R.color.white))
            btnBreakfast.setTextColor(resources.getColor(R.color.black))

            /* viewModel.getFoodSearchObservable().observe(viewLifecycleOwner, Observer<FoodShortDescList> {
                 adapterFoodOrderShortDesc.foodDescList = it.data.toMutableList()
                 adapterFoodOrderShortDesc.notifyDataSetChanged()
             })*/
            viewModel.APICallSearchFood("1")

        }

    }

    fun getSubCategoryData(){
        viewModel.getSubCategoryObservable().observe(viewLifecycleOwner, Observer<SubCategoryResponseModel> {
          adapterBreakFastSubCategory.subCategoryList = it.subCategoryData?.toMutableList()
          adapterBreakFastSubCategory.notifyDataSetChanged()
        })
        viewModel.APICallSubCategory()
    }



}


/*
https://abhiandroid.com/ui/calendarview
*/





