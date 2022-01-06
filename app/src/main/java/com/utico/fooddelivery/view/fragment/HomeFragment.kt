package com.utico.fooddelivery.view.fragment

import android.content.Context
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
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.utico.fooddelivery.R
import com.utico.fooddelivery.`interface`.CallbackHome
import com.utico.fooddelivery.adapter.*
import com.utico.fooddelivery.databinding.FragmentHomeBinding
import com.utico.fooddelivery.model.*
import com.utico.fooddelivery.view.LoginActivity
import com.utico.fooddelivery.view.RegistrationActivity
import com.utico.fooddelivery.viewmodel.HomeViewModel


class HomeFragment : Fragment(),CallbackHome{

    private lateinit var viewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    lateinit var adapterSubCategory: AdapterSubCategory
    lateinit var subscriptionPackageAdapter: SubscriptionPackageAdapter
    private lateinit var btnBreakfast: Button
    private lateinit var btnBrunch: Button
    private lateinit var btnLogout: ImageView
    private var breakfastItemMainCategoryName: String? = "Breakfast"
    private var brunchItemMainCategoryName: String? = "Brunch"
    private var itemFoodType: String? = "veg"
    private var breakfastMainCategoryId: String? = "1"
    private var brunchMainCategoryId: String? = "2"
    private var subscriptionId: String? = "3"
    private var breakfastSubCategoryName: String? = "All Day"
    private var brunchSubCategoryName: String? = "Starters"
    private var registrationSharedPreferences: SharedPreferences? = null
    private var userId: String? = null
    private var searchFilterMenuList = mutableListOf<MenuDetailsResponseDataModel>()
    private var mainCategoryName: String? = "Breakfast"
    private lateinit var mainCategoryListAdapter:MainCategoryListAdapter

/*
    private lateinit var AdapterSubCategory : AdapterSubCategory()
*/

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.homeViewModel = viewModel
        btnBreakfast = binding.btnBreakfast
        btnBrunch = binding.btnBrunch
        btnLogout = binding.logoutImageview
        /*setHasOptionsMenu(true)*/
        registrationSharedPreferences = context?.getSharedPreferences(
            resources.getString(R.string.registration_details_sharedPreferences),
            Context.MODE_PRIVATE
        )
        userId = registrationSharedPreferences?.getString("userId", "")
        val view: View = binding.root

        val toolbar = binding.homeCustomToolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)



        btnLogout.setOnClickListener {
            if (userId.equals("") || userId.equals(null)) {
                Toast.makeText(context, "Before Add to Cart Please Register!!", Toast.LENGTH_LONG)
                    .show()
                val intent = Intent(context, RegistrationActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(context, LoginActivity::class.java)
                startActivity(intent)
                (activity as AppCompatActivity).finish()
            }
        }

       /* binding.edtSearch.doOnTextChanged { text, start, before, count ->
            val searchText = text.toString().toLowerCase(Locale.getDefault())
            filterWithSearchText(searchText, mainCategoryName!!)
        }

        binding.breakfastVegSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                filterWithSearchText(itemFoodType!!, mainCategoryName!!)
            } else {
                getBreakfastMenuDetails(breakfastItemMainCategoryName!!)

            }*/
       // }


      /*  binding.brunchVegSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                filterWithSearchText(itemFoodType!!, mainCategoryName!!)
            } else {
                getBrunchMenuDetails(brunchItemMainCategoryName!!)

            }
        }*/

        initAdapter()
        fetchMainCategoryList()
        //getBreakfastMenuDetails(breakfastItemMainCategoryName!!)
        // searchFood()
        // buttonBreakfast()
        // buttonmBrunch()
        // getBreakfastSubCategoryData()
       //  setClickEvents()
        // vegOrNonVegSwitch()
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

    fun initAdapter() {


       /* val breakfastSubCategoryRecyclerview = binding.breakfastSubCategoryRecyclerview
        breakfastSubCategoryRecyclerview.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        adapterBreakFastSubCategory = AdapterBreakfastSubCategory(this)
        breakfastSubCategoryRecyclerview.adapter = adapterBreakFastSubCategory*/

      /*  val brunchSubcategoryRecyclerView = binding.brunchSubCategoryRecyclerview
        brunchSubcategoryRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        adapterBrunchSubCategory = AdapterBrunchSubCategory(this)
        brunchSubcategoryRecyclerView.adapter = adapterBrunchSubCategory
*/

        /*MainCategory Listing Adapter*/
        val mainCategoryRecyclerView = binding.maiCategoryRecyclerview
            mainCategoryRecyclerView.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            mainCategoryListAdapter = MainCategoryListAdapter(this)
            mainCategoryRecyclerView.adapter = mainCategoryListAdapter

        /*SubCategory Listing Adapter*/
        val subCategoryRecyclerView = binding.subCategoryRecyclerView
        subCategoryRecyclerView.layoutManager = GridLayoutManager(activity, 2)
        /* val decoration  = DividerItemDecoration(activity,DividerItemDecoration.VERTICAL)
         foodShortxDescrecyclerview.addItemDecoration(decoration)*/
        adapterSubCategory = AdapterSubCategory()
        subCategoryRecyclerView.adapter = adapterSubCategory

        /*Subscription RecyclerView*/
        val subscriptionRecyclerView = binding.subscriptionRecyclerView
        subscriptionRecyclerView.layoutManager = LinearLayoutManager(activity)
        subscriptionPackageAdapter = SubscriptionPackageAdapter()
        subscriptionRecyclerView.adapter = subscriptionPackageAdapter


      /*  val brunchRecyclerView = binding.brunchRecyclerview
        brunchRecyclerView.layoutManager = LinearLayoutManager(activity)
        adapterBrunchDescription = AdapterBrunchDescription()
        brunchRecyclerView.adapter = adapterBrunchDescription*/

    }


    fun buttonBreakfast() {
        val breakfastRecyclerview = binding.subCategoryRecyclerView
        val brunchRecyclerview = binding.brunchRecyclerview
        val breakfastVegSwitch = binding.breakfastVegSwitch
        val brunchVegSwitch = binding.brunchVegSwitch
        val breakfastSubCategoryRecyclerView = binding.breakfastSubCategoryRecyclerview
        val brunchSubCategoryRecyclerview = binding.brunchSubCategoryRecyclerview
        val breakfastBannerImageView = binding.breakfastBannerImageView
        val brunchBannerImageView = binding.brunchBannerImageView

        btnBreakfast.setOnClickListener {
           // getBreakfastSubCategoryData()
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
            mainCategoryName =
                "Breakfast"//This is used to search filter for the Breakfast Menu details
            //getBreakfastMenuDetails(breakfastItemMainCategoryName!!)

        }

    }


    fun buttonmBrunch() {
        val breakfastRecyclerview = binding.subCategoryRecyclerView
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
            brunchBannerImageView.visibility = View.VISIBLE

            btnBrunch.setBackgroundColor(resources.getColor(R.color.green))
            btnBrunch.setTextColor(resources.getColor(R.color.white))
            btnBreakfast.setBackgroundColor(resources.getColor(R.color.white))
            btnBreakfast.setTextColor(resources.getColor(R.color.black))
            mainCategoryName = "Brunch"//this is used to search filter for Brunch
            /*set the subcategory data*/
           // getBrunchMenuDetails(brunchItemMainCategoryName!!)
        }

    }


   /* *//*This method is used to get the *//*
    fun getBreakfastMenuDetails(itemMainCategoryName: String) {
        viewModel.getMenuDetailObservable()
            .observe(viewLifecycleOwner, Observer<MenuDetailsResponseDataModel> {
                if (it.menuData == null) {
                    Toast.makeText(
                        context,
                        "Data not found" + " " + it.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    *//* adapterSubCategory.subCategoryList = it.menuData.data.toMutableList()
                     adapterSubCategory.notifyDataSetChanged()
                     searchFilterMenuList = it.menuData.data.toMutableList()*//*
                }
            })
        viewModel.ApiCallMenuDetails(itemMainCategoryName!!)
    }*/

    /*Search Filter*/
  /*  private fun filterWithSearchText(searchText: String, mainCategoryName: String) {
        //https://developersbreach.com/search-recyclerview-with-query-voice-android/
        val filteredList = mutableListOf<DataX>()
        for (menuTitle in searchFilterMenuList) {
            if (menuTitle.itemName.toLowerCase(Locale.getDefault())
                    .contains(searchText) || menuTitle.itemFoodType.toLowerCase(Locale.getDefault())
                    .contains(searchText) ||
                menuTitle.itemDescription.toLowerCase(Locale.getDefault()).contains(searchText)
            ) {
                filteredList.add(menuTitle)
            }
        }
        if (mainCategoryName.equals("Breakfast")) {
            *//* adapterSubCategory.subCategoryList = filteredList
             adapterSubCategory.notifyDataSetChanged()*//*
        } else {
            adapterBrunchDescription.brunchDescList = filteredList
            adapterBrunchDescription.notifyDataSetChanged()
        }
    }*/


    /*This method is used to get the Brunch related data */
    /*fun getBrunchMenuDetails(itemMainCategoryName: String) {
        *//*get the brunch Menu details list*//*
        viewModel.getMenuDetailObservable()
            .observe(viewLifecycleOwner, Observer<MenuDetailsResponseModel> {
                if (it.menuData == null) {
                    Toast.makeText(
                        context,
                        "Data not found" + " " + it.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    adapterBrunchDescription.brunchDescList = it.menuData.data.toMutableList()
                    searchFilterMenuList = it.menuData.data.toMutableList()
                    adapterBrunchDescription.notifyDataSetChanged()
                }
            })
        viewModel.ApiCallMenuDetails(itemMainCategoryName!!)

        *//*get the brunch Category list*//*
        viewModel.getSubCategoryObservable()
            .observe(viewLifecycleOwner, Observer<SubCategoryResponseModel> {
                if (it.SubCategoryData == null) {
                    Toast.makeText(context, it.statusCode, Toast.LENGTH_SHORT).show()
                } else {
                    adapterBrunchSubCategory.brunchSubCategoryList =
                        it.SubCategoryData?.toMutableList()
                    adapterBrunchSubCategory.notifyDataSetChanged()
                }
            })
        viewModel.APICallSubCategory(brunchMainCategoryId!!)


    }*/


   /* fun getBreakfastSubCategoryData() {
        viewModel.getSubCategoryObservable()
            .observe(viewLifecycleOwner, Observer<SubCategoryResponseModel> {
                if (it.SubCategoryData == null) {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                } else {
                    adapterBreakFastSubCategory.breakfastSubCategoryList =
                        it.SubCategoryData?.toMutableList()
                    adapterBreakFastSubCategory.notifyDataSetChanged()
                }
            })
        viewModel.APICallSubCategory(breakfastMainCategoryId!!)
    }*/


   /* override fun getBreakfastSubCategoryName(itemSubCategoryName: String, isCheck: Boolean) {
        if (isCheck == true) {
            breakfastSubCategoryName = itemSubCategoryName
            viewModel.subCategoryMenuDetailsObservable()
                .observe(viewLifecycleOwner, Observer<SubCategoryMenuDetailsModel> {
                    if (it.subCategoryMenuData == null) {
                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    } else {
                        *//* adapterSubCategory.subCategoryList =
                             it.subCategoryMenuData.data.toMutableList()
                         adapterSubCategory.notifyDataSetChanged()*//*
                    }

                })
            viewModel.apiCallSubCategoryMenuDetails(
                breakfastItemMainCategoryName!!,
                itemSubCategoryName!!
            )
        } else {
            getBreakfastMenuDetails(breakfastItemMainCategoryName!!)
        }
    }
*/
   /* override fun getBrunchSubCategoryRelatedMenuDetails(
        itemSubCategoryName: String,
        isCheck: Boolean
    ) {
        if (isCheck == true) {
            brunchSubCategoryName = itemSubCategoryName
            viewModel.subCategoryMenuDetailsObservable()
                .observe(viewLifecycleOwner, Observer<SubCategoryMenuDetailsModel> {
                    if (it.subCategoryMenuData == null) {
                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    } else {
                        adapterBrunchDescription.brunchDescList =
                            it.subCategoryMenuData.data.toMutableList()
                        adapterBrunchDescription.notifyDataSetChanged()
                    }
                })
            viewModel.apiCallSubCategoryMenuDetails(
                brunchItemMainCategoryName!!,
                itemSubCategoryName!!
            )
        } else {
            getBrunchMenuDetails(brunchItemMainCategoryName!!)
        }

    }*/




   /* private fun setClickEvents() {
        val tvBreakfast = binding.tvBreakfast
        val tvBrunch = binding.tvBrunch
        val tvSubscription = binding.tvSubscription
        val viewLineBreakfast = binding.viewLineBreakfast
        val viewLineBrunch = binding.viewLineBrunch
        val viewLineSubscription = binding.viewLineSubscription
        val subCategoryRecyclerView = binding.subCategoryRecyclerView
        val subscriptionRecyclerView = binding.subscriptionRecyclerView

        getSubCategory(breakfastMainCategoryId!!)

        tvBreakfast.setOnClickListener {
            viewLineBrunch.visibility = View.GONE
            viewLineSubscription.visibility = View.GONE
            subscriptionRecyclerView.visibility = View.GONE
            subCategoryRecyclerView.visibility = View.VISIBLE
            viewLineBreakfast.visibility = View.VISIBLE
            tvBreakfast.setTypeface(null, Typeface.BOLD)
            tvBrunch.setTypeface(null, Typeface.NORMAL)
            tvSubscription.setTypeface(null, Typeface.NORMAL)
            getSubCategory(breakfastMainCategoryId!!)
        }//This is the click event for the Breakfast


        tvBrunch.setOnClickListener {
            viewLineBrunch.visibility = View.VISIBLE
            subCategoryRecyclerView.visibility = View.VISIBLE
            viewLineBreakfast.visibility = View.GONE
            subscriptionRecyclerView.visibility = View.GONE
            viewLineSubscription.visibility = View.GONE
            tvBrunch.setTypeface(null, Typeface.BOLD)
            tvBreakfast.setTypeface(null, Typeface.NORMAL)
            tvSubscription.setTypeface(null, Typeface.NORMAL)
            getSubCategory(brunchMainCategoryId!!)
        }//Click event for the Brunch

        tvSubscription.setOnClickListener {
            subCategoryRecyclerView.visibility = View.GONE
            subscriptionRecyclerView.visibility = View.VISIBLE
            viewLineSubscription.visibility = View.VISIBLE
            viewLineBreakfast.visibility = View.GONE
            viewLineBrunch.visibility = View.GONE
            tvSubscription.setTypeface(null, Typeface.BOLD)
            tvBreakfast.setTypeface(null, Typeface.NORMAL)
            tvBrunch.setTypeface(null, Typeface.NORMAL)
            getSubscriptionTypes()
        }//Click event for subscription
    }*/


    override fun mainCategoryClickEvent(mainCategoryId: String,position:Int) {
        if (position>=2){
            binding.subscriptionRecyclerView.visibility = View.VISIBLE
            binding.subCategoryRecyclerView.visibility = View.GONE
            getSubscriptionTypes()

        }else {
            getSubCategory(mainCategoryId)
            binding.subscriptionRecyclerView.visibility = View.GONE
            binding.subCategoryRecyclerView.visibility = View.VISIBLE
        }
    }

    private fun getSubCategory(mainCategoryId: String) {
        viewModel.getSubCategoryObservable()
            .observe(viewLifecycleOwner, Observer<SubCategoryResponseModel> {
                if (it.statusCode == 400) {
                    adapterSubCategory.notifyDataSetChanged()
                    adapterSubCategory.subCategoryList.clear()
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                } else {
                    adapterSubCategory.subCategoryList = it.SubCategoryData?.toMutableList()
                    adapterSubCategory.notifyDataSetChanged()
                }
            })
        viewModel.APICallSubCategory(mainCategoryId!!)
    }//This Method is used to get the Breakfast Category name


    private fun getSubscriptionTypes() {
        viewModel.subscriptionTypeObservable()
            .observe(viewLifecycleOwner, Observer<SubscriptionTitleDataResponseModel> {
                if (it.statusCode == 400) {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                } else {
                    subscriptionPackageAdapter.subscriptionTypeList =
                        it.SubscriptionTitle.toMutableList()
                    subscriptionPackageAdapter.notifyDataSetChanged()
                }
            })
        viewModel.apiCallSubscriptionTypes()
    }


    private fun fetchMainCategoryList(){
      viewModel.mainCategoryObservable().observe(viewLifecycleOwner, Observer<MainCategoryResponseModel> {
        if (it.statusCode == 400) {
            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
        }else{
           mainCategoryListAdapter.mainCategoryList = it.MainCategoryData.toMutableList()
            mainCategoryListAdapter.notifyDataSetChanged()
        }
      })
      viewModel.apiCallForMainCategoryListing()
    }


}








