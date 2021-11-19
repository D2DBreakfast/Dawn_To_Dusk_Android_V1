package com.utico.fooddelivery.view.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Typeface
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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.utico.fooddelivery.R
import com.utico.fooddelivery.`interface`.CallbackBreakfastSubCategoryName
import com.utico.fooddelivery.`interface`.BrunchSubCategoryMenuDetailsListener
import com.utico.fooddelivery.adapter.*
import com.utico.fooddelivery.databinding.FragmentHomeBinding
import com.utico.fooddelivery.model.DataX
import com.utico.fooddelivery.model.MenuDetailsResponseModel
import com.utico.fooddelivery.model.SubCategoryMenuDetailsModel
import com.utico.fooddelivery.model.SubCategoryResponseModel
import com.utico.fooddelivery.view.LoginActivity
import com.utico.fooddelivery.view.RegistrationActivity
import com.utico.fooddelivery.viewmodel.HomeViewModel
import java.util.*


class HomeFragment : Fragment(),CallbackBreakfastSubCategoryName,BrunchSubCategoryMenuDetailsListener{

    private lateinit var viewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    lateinit var adapterSubCategory: AdapterSubCategory
    lateinit var adapterBrunchDescription: AdapterBrunchDescription
    lateinit var adapterBreakFastSubCategory: AdapterBreakfastSubCategory
    lateinit var adapterBrunchSubCategory: AdapterBrunchSubCategory
    private lateinit var btnBreakfast:Button
    private lateinit var btnBrunch:Button
    private lateinit var  btnLogout:ImageView
    private var breakfastItemMainCategoryName:String? = "Breakfast"
    private var brunchItemMainCategoryName:String? = "Brunch"
    private var itemFoodType:String? = "veg"
    private var breakfastMainCategoryId:String?="1"
    private var brunchMainCategoryId:String?="2"
    private var breakfastSubCategoryName:String? ="All Day"
    private var brunchSubCategoryName:String? ="Starters"
    private var registrationSharedPreferences:SharedPreferences? = null
    private var userId:String? = null
    private var searchFilterMenuList = mutableListOf<DataX>()
    private var mainCategoryName:String?="Breakfast"

/*
    private lateinit var AdapterSubCategory : AdapterSubCategory()
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
        registrationSharedPreferences = context?.getSharedPreferences(resources.getString(R.string.registration_details_sharedPreferences),Context.MODE_PRIVATE)
        userId = registrationSharedPreferences?.getString("userId","")
        val view: View = binding.root

        val toolbar = binding.homeCustomToolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)



           btnLogout.setOnClickListener {
               if (userId.equals("")||userId.equals(null)){
                   Toast.makeText(context,"Before Add to Cart Please Register!!",Toast.LENGTH_LONG).show()
                   val intent = Intent(context, RegistrationActivity::class.java)
                       startActivity(intent)
               }else {
                   val intent = Intent(context, LoginActivity::class.java)
                      startActivity(intent)
                      (activity as AppCompatActivity).finish()
               }
           }

        binding.edtSearch.doOnTextChanged { text, start, before, count ->
            val searchText = text.toString().toLowerCase(Locale.getDefault())
                filterWithSearchText(searchText,mainCategoryName!!)
        }

       binding.breakfastVegSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
           if (isChecked){
               filterWithSearchText(itemFoodType!!,mainCategoryName!!)
           }else{
               getBreakfastMenuDetails(breakfastItemMainCategoryName!!)

           }
       }


        binding.brunchVegSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                filterWithSearchText(itemFoodType!!,mainCategoryName!!)
            }else{
                getBrunchMenuDetails(brunchItemMainCategoryName!!)

            }
        }

        initAdapter()
        //getBreakfastMenuDetails(breakfastItemMainCategoryName!!)
       // searchFood()
       // buttonBreakfast()
       // buttonmBrunch()
       // getBreakfastSubCategoryData()
        setClickEvents()
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

  fun  initAdapter() {


      val breakfastSubCategoryRecyclerview = binding.breakfastSubCategoryRecyclerview
           breakfastSubCategoryRecyclerview.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
           adapterBreakFastSubCategory = AdapterBreakfastSubCategory(this)
           breakfastSubCategoryRecyclerview.adapter = adapterBreakFastSubCategory

      val brunchSubcategoryRecyclerView = binding.brunchSubCategoryRecyclerview
          brunchSubcategoryRecyclerView.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
          adapterBrunchSubCategory = AdapterBrunchSubCategory(this)
          brunchSubcategoryRecyclerView.adapter = adapterBrunchSubCategory




      val subCategoryRecyclerView = binding.subCategoryRecyclerView
          subCategoryRecyclerView.layoutManager = GridLayoutManager(activity,2)
         /* val decoration  = DividerItemDecoration(activity,DividerItemDecoration.VERTICAL)
          foodShortxDescrecyclerview.addItemDecoration(decoration)*/
           adapterSubCategory = AdapterSubCategory()
           subCategoryRecyclerView.adapter = adapterSubCategory



      val brunchRecyclerView = binding.brunchRecyclerview
          brunchRecyclerView.layoutManager = LinearLayoutManager(activity)
          adapterBrunchDescription = AdapterBrunchDescription()
          brunchRecyclerView.adapter = adapterBrunchDescription

  }


    fun buttonBreakfast(){
        val breakfastRecyclerview = binding.subCategoryRecyclerView
        val brunchRecyclerview = binding.brunchRecyclerview
        val breakfastVegSwitch = binding.breakfastVegSwitch
        val brunchVegSwitch = binding.brunchVegSwitch
        val breakfastSubCategoryRecyclerView = binding.breakfastSubCategoryRecyclerview
        val brunchSubCategoryRecyclerview = binding.brunchSubCategoryRecyclerview
        val breakfastBannerImageView = binding.breakfastBannerImageView
        val brunchBannerImageView = binding.brunchBannerImageView

        btnBreakfast.setOnClickListener {
            getBreakfastSubCategoryData()
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
            mainCategoryName ="Breakfast"//This is used to search filter for the Breakfast Menu details
           getBreakfastMenuDetails(breakfastItemMainCategoryName!!)

        }

    }



    fun buttonmBrunch(){
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
            brunchBannerImageView.visibility =View.VISIBLE

            btnBrunch.setBackgroundColor(resources.getColor(R.color.green))
            btnBrunch.setTextColor(resources.getColor(R.color.white))
            btnBreakfast.setBackgroundColor(resources.getColor(R.color.white))
            btnBreakfast.setTextColor(resources.getColor(R.color.black))
            mainCategoryName ="Brunch"//this is used to search filter for Brunch
             /*set the subcategory data*/
            getBrunchMenuDetails(brunchItemMainCategoryName!!)
        }
    }


    /*This method is used to get the */
    fun getBreakfastMenuDetails(itemMainCategoryName:String){
        viewModel.getMenuDetailObservable().observe(viewLifecycleOwner, Observer<MenuDetailsResponseModel> {
            if (it.menuData==null){
                Toast.makeText(context,"Data not found"+ " "+it.toString(),Toast.LENGTH_SHORT).show()
            }else {
               /* adapterSubCategory.subCategoryList = it.menuData.data.toMutableList()
                adapterSubCategory.notifyDataSetChanged()
                searchFilterMenuList = it.menuData.data.toMutableList()*/
            }
        })
        viewModel.ApiCallMenuDetails(itemMainCategoryName!!)
    }

   /*Search Filter*/
    private fun filterWithSearchText(searchText: String,mainCategoryName:String) {
        //https://developersbreach.com/search-recyclerview-with-query-voice-android/
        val filteredList = mutableListOf<DataX>()
       for (menuTitle in searchFilterMenuList){
           if (menuTitle.itemName.toLowerCase(Locale.getDefault()).contains(searchText)||menuTitle.itemFoodType.toLowerCase(Locale.getDefault()).contains(searchText)||
                   menuTitle.itemDescription.toLowerCase(Locale.getDefault()).contains(searchText)){
              filteredList.add(menuTitle)
           }
       }
        if (mainCategoryName.equals("Breakfast")) {
           /* adapterSubCategory.subCategoryList = filteredList
            adapterSubCategory.notifyDataSetChanged()*/
        }else{
            adapterBrunchDescription.brunchDescList = filteredList
            adapterBrunchDescription.notifyDataSetChanged()
        }
    }



    /*This method is used to get the Brunch related data */
    fun getBrunchMenuDetails(itemMainCategoryName:String){
        /*get the brunch Menu details list*/
        viewModel.getMenuDetailObservable().observe(viewLifecycleOwner, Observer<MenuDetailsResponseModel> {
            if (it.menuData==null){
                Toast.makeText(context,"Data not found"+ " "+it.toString(),Toast.LENGTH_SHORT).show()
            }else {
                adapterBrunchDescription.brunchDescList = it.menuData.data.toMutableList()
                searchFilterMenuList = it.menuData.data.toMutableList()
                adapterBrunchDescription.notifyDataSetChanged()
            }
        })
        viewModel.ApiCallMenuDetails(itemMainCategoryName!!)

        /*get the brunch Category list*/
        viewModel.getSubCategoryObservable().observe(viewLifecycleOwner, Observer<SubCategoryResponseModel> {
            if (it.subCategoryData==null){
                Toast.makeText(context,it.message,Toast.LENGTH_SHORT).show()
            }else{
                adapterBrunchSubCategory.brunchSubCategoryList = it.subCategoryData?.toMutableList()
                adapterBrunchSubCategory.notifyDataSetChanged()
            }
        })
        viewModel.APICallSubCategory(brunchMainCategoryId!!)


    }


    fun getBreakfastSubCategoryData(){
        viewModel.getSubCategoryObservable().observe(viewLifecycleOwner, Observer<SubCategoryResponseModel> {
           if (it.subCategoryData==null){
               Toast.makeText(context,it.message,Toast.LENGTH_SHORT).show()
           }else{
               adapterBreakFastSubCategory.breakfastSubCategoryList = it.subCategoryData?.toMutableList()
               adapterBreakFastSubCategory.notifyDataSetChanged()
           }
        })
        viewModel.APICallSubCategory(breakfastMainCategoryId!!)
    }


    override fun getBreakfastSubCategoryName(itemSubCategoryName: String, isCheck: Boolean) {
        if (isCheck==true) {
            breakfastSubCategoryName = itemSubCategoryName
            viewModel.subCategoryMenuDetailsObservable()
                .observe(viewLifecycleOwner, Observer<SubCategoryMenuDetailsModel> {
                    if (it.subCategoryMenuData == null) {
                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    } else {
                       /* adapterSubCategory.subCategoryList =
                            it.subCategoryMenuData.data.toMutableList()
                        adapterSubCategory.notifyDataSetChanged()*/
                    }

                })
            viewModel.apiCallSubCategoryMenuDetails(breakfastItemMainCategoryName!!, itemSubCategoryName!!)
        }else{
            getBreakfastMenuDetails(breakfastItemMainCategoryName!!)
        }
    }



    override fun getBrunchSubCategoryRelatedMenuDetails(itemSubCategoryName: String, isCheck: Boolean) {
            if (isCheck==true){
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
                viewModel.apiCallSubCategoryMenuDetails(brunchItemMainCategoryName!!, itemSubCategoryName!!)
            }else{
                getBrunchMenuDetails(brunchItemMainCategoryName!!)
            }

        }



       private fun setClickEvents(){
           val tvBreakfast = binding.tvBreakfast
           val tvBrunch = binding.tvBrunch
           val viewLineBreakfast = binding.viewLineBreakfast
           val viewLineBrunch = binding.viewLineBrunch
                getSubCategory(breakfastMainCategoryId!!)

            tvBreakfast.setOnClickListener {
            viewLineBrunch.visibility = View.GONE
            viewLineBreakfast.visibility = View.VISIBLE
            tvBreakfast.setTypeface(null, Typeface.BOLD)
            tvBrunch.setTypeface(null,Typeface.NORMAL)
                getSubCategory(breakfastMainCategoryId!!)
           }//This is the click event for the Breakfast


           tvBrunch.setOnClickListener {
             viewLineBrunch.visibility = View.VISIBLE
             viewLineBreakfast.visibility = View.GONE
             tvBrunch.setTypeface(null,Typeface.BOLD)
             tvBreakfast.setTypeface(null,Typeface.NORMAL)
               getSubCategory(brunchMainCategoryId!!)
           }//Click event for the Brunch
       }


    private fun getSubCategory(mainCategoryId:String){
        viewModel.getSubCategoryObservable().observe(viewLifecycleOwner, Observer<SubCategoryResponseModel> {
            if (it.subCategoryData==null){
                Toast.makeText(context,it.message,Toast.LENGTH_SHORT).show()
            }else{
                adapterSubCategory.subCategoryList = it.subCategoryData?.toMutableList()
                adapterSubCategory.notifyDataSetChanged()
            }
        })
        viewModel.APICallSubCategory(mainCategoryId!!)
    }//This Method is used to get the Breakfast Category name

}








