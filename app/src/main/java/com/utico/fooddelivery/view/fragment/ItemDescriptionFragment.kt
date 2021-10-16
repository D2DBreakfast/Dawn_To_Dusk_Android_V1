package com.utico.fooddelivery.view.fragment

import android.app.DatePickerDialog
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import com.utico.fooddelivery.R
import com.utico.fooddelivery.databinding.ItemDescriptionFragmentBinding
import com.utico.fooddelivery.viewmodel.ItemDescriptionViewModel
import retrofit2.http.Field
import java.util.*

class ItemDescriptionFragment : Fragment() {

    private val bundle = Bundle()
    private var userId:String? = null
    private var itemName:String? = null
    private var itemMainCategoryName:String? = null
    private var itemSubCategoryName:String? = null
    private var itemFoodType:String? = null
    private var itemQuantity:String? = null
    private var itemPrice:String? = null
    private var itemDescription:String? = null
    private var itemId:String? = null
    private lateinit var binding: ItemDescriptionFragmentBinding
    private var sharedPreferences:SharedPreferences? = null
    private var registrationSharedPreferences:SharedPreferences? = null


    companion object {
        fun newInstance() = ItemDescriptionFragment()
    }


    lateinit var viewModel: ItemDescriptionViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ItemDescriptionViewModel::class.java)
        binding = ItemDescriptionFragmentBinding.inflate(inflater,container,false)


        /*foodName = arguments?.getString("foodName","")
        foodDescription = arguments?.getString("foodDescription","")
        foodPrice = arguments?.getString("foodPrice","")*/
      /* foodName = bundle.getString("foodName")
        foodDescription = bundle.getString("foodDescription")
        foodPrice = bundle.getString("foodPrice")*/
/*
        val view = inflater.inflate(R.layout.item_description_fragment, container, false)
*/
        sharedPreferences=context?.getSharedPreferences(resources.getString(R.string.order_details_sharedPreferences), Context.MODE_PRIVATE)
        registrationSharedPreferences = context?.getSharedPreferences(resources.getString(R.string.registration_details_sharedPreferences),Context.MODE_PRIVATE)
        userId = registrationSharedPreferences?.getString("userId","")
        itemName = sharedPreferences?.getString("itemName","")
        itemMainCategoryName =sharedPreferences?.getString("itemMainCategoryName","")
        itemSubCategoryName = sharedPreferences?.getString("itemSubCategoryName","")
        itemFoodType = sharedPreferences?.getString("itemFoodType","")
        itemQuantity = sharedPreferences?.getString("itemQuantity","")
        itemPrice = sharedPreferences?.getString("itemPrice","")
        itemDescription = sharedPreferences?.getString("itemDescription","")
        itemId = sharedPreferences?.getString("itemId","")
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
     binding.tvTitle.setText(itemName)
     binding.tvDesc.setText(itemDescription)
     binding.tvPrice.setText(itemPrice)
     binding.tvnutrionalInfo.setText(itemDescription)
     binding.addToCart.setOnClickListener {
        viewModel.postCartObservable().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
          Toast.makeText(context,it,Toast.LENGTH_SHORT).show()
        })
         viewModel.apiCall(itemMainCategoryName!!,itemSubCategoryName!!,itemFoodType!!,itemName!!,itemId!!,itemQuantity!!,itemPrice!!,userId!!)
     }
 }



}


