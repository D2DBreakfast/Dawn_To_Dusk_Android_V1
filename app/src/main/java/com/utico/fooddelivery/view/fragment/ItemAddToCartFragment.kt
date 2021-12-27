package com.utico.fooddelivery.view.fragment

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.utico.fooddelivery.R
import com.utico.fooddelivery.databinding.ItemAddtocartFragmentBinding
import com.utico.fooddelivery.view.DashBoardActivity
import com.utico.fooddelivery.viewmodel.ItemAddToCartViewModel
import java.util.*

class ItemAddToCartFragment : Fragment() {

    private val bundle = Bundle()
    private var userId:String? = null
    private var itemName:String? = null
    private var itemMainCategoryName:String? = null
    private var itemSubCategoryName:String? = null
    private var itemFoodType:Boolean? = null
    private var itemQuantity:String? = null
    private var itemPrice:String? = null
    private var itemDescription:String? = null
    private var itemId:String? = null
    private var itemImageUrl:String? = null
    private var itemBasePrice:String? = null

    private lateinit var binding: ItemAddtocartFragmentBinding
    private var sharedPreferences:SharedPreferences? = null
    private var registrationSharedPreferences:SharedPreferences? = null



    companion object {
        fun newInstance() = ItemAddToCartFragment()
    }


    lateinit var viewModel: ItemAddToCartViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ItemAddToCartViewModel::class.java)
        binding = ItemAddtocartFragmentBinding.inflate(inflater,container,false)


        /*foodName = arguments?.getString("foodName","")
        foodDescription = arguments?.getString("foodDescription","")
        foodPrice = arguments?.getString("foodPrice","")*/
      /* foodName = bundle.getString("foodName")
        foodDescription = bundle.getString("foodDescription")
        foodPrice = bundle.getString("foodPrice")*/
/*
        val view = inflater.inflate(R.layout.item_addtocart_fragment, container, false)
*/
        sharedPreferences=context?.getSharedPreferences(resources.getString(R.string.order_details_sharedPreferences), Context.MODE_PRIVATE)
        registrationSharedPreferences = context?.getSharedPreferences(resources.getString(R.string.registration_details_sharedPreferences),Context.MODE_PRIVATE)
        userId = registrationSharedPreferences?.getString("userId","")
        itemName = sharedPreferences?.getString("itemName","")
        itemMainCategoryName =sharedPreferences?.getString("itemMainCategoryName","")
        itemSubCategoryName = sharedPreferences?.getString("itemSubCategoryName","")
      //  itemFoodType = sharedPreferences?.getString("itemFoodType","")
        itemQuantity = sharedPreferences?.getString("itemBaseQuantity","")
        itemPrice = sharedPreferences?.getString("itemPrice","")
        itemDescription = sharedPreferences?.getString("itemDescription","")
        itemId = sharedPreferences?.getString("itemId","")
        itemImageUrl = sharedPreferences?.getString("itemImageUrl","")
        itemBasePrice = sharedPreferences?.getString("itemPrice","")


        val view:View = binding.root

           datePicker()
           initView()
        return view
    }

    fun datePicker(){
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val lnrCalendar = binding.lnrCalendar
        val tvDate = binding.tvDate
             lnrCalendar.setOnClickListener {
                 val datePicker = DatePickerDialog(activity as Context,DatePickerDialog.OnDateSetListener{view, year, month, dayOfMonth ->
                     tvDate.setText(""+ dayOfMonth + "/" + month + "/" + year)
                 },year,month,day)
                 datePicker.getDatePicker().setMinDate(calendar.getTimeInMillis());
                 datePicker.show()
             }
    }


 fun initView(){
    // binding.tvCategory.setText(itemSubCategoryName)
     binding.tvItemName.setText(itemName)
     binding.tvDescription.setText(itemDescription)
     binding.tvPrice.setText("AED"+" "+itemPrice)
     binding.tvDescription.setText(itemDescription)

     if (itemFoodType == true){
         binding.vegOrNonVegImageView.setBackgroundResource(R.drawable.veg)
     }else{
         binding.vegOrNonVegImageView.setBackgroundResource(R.drawable.non_veg)

     }

     /*Picasso.get()
         .load(itemImageUrl)
         .into(binding.imageView)*/
     binding.buttonAddToCart.setOnClickListener {
        viewModel.postCartObservable().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
          Toast.makeText(context,"This Item Add to Cart Successful and go and check the Car Page!!",Toast.LENGTH_SHORT).show()
            val intent = Intent(context,DashBoardActivity::class.java)
            context?.startActivity(intent)
        })
         viewModel.apiCall(itemMainCategoryName!!,itemSubCategoryName!!,itemFoodType!!,itemName!!,itemId!!,itemQuantity!!,itemPrice!!,itemImageUrl!!,itemDescription!!,userId!!,itemBasePrice!!)
     }
 }



}


