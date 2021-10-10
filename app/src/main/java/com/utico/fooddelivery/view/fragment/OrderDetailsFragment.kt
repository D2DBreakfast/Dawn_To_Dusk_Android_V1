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
import com.utico.fooddelivery.R
import com.utico.fooddelivery.`interface`.PassFoodDescDataListener
import com.utico.fooddelivery.adapter.AdapterFoodOrderShortDesc
import com.utico.fooddelivery.databinding.OrderDetailsFragmentBinding
import com.utico.fooddelivery.viewmodel.OrderDetailsViewModel
import java.util.*

class OrderDetailsFragment : Fragment() {


    private val bundle = Bundle()
    var foodName:String? = null
    var foodDescription:String? = null
    var foodPrice:String? = null
    private lateinit var binding: OrderDetailsFragmentBinding
    var sharedPreferences:SharedPreferences? = null

    companion object {
        fun newInstance() = OrderDetailsFragment()
    }


    private lateinit var viewModel: OrderDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

       /*foodName = arguments?.getString("foodName","")
       foodDescription = arguments?.getString("foodDescription","")
       foodPrice = arguments?.getString("foodPrice","")*/
      /* foodName = bundle.getString("foodName")
        foodDescription = bundle.getString("foodDescription")
        foodPrice = bundle.getString("foodPrice")*/
/*
        val view = inflater.inflate(R.layout.order_details_fragment, container, false)
*/
        sharedPreferences=context?.getSharedPreferences(resources.getString(R.string.order_details_sharedPreferences), Context.MODE_PRIVATE)
        foodName = sharedPreferences?.getString("foodName","")
        foodDescription = sharedPreferences?.getString("foodDescription","")
        foodPrice = sharedPreferences?.getString("foodPrice","")


        binding = OrderDetailsFragmentBinding.inflate(inflater,container,false)
            val view:View = binding.root

           datePicker()
           initView()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OrderDetailsViewModel::class.java)
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
     binding.tvTitle.setText(foodName)
     binding.tvDesc.setText(foodDescription)
     binding.tvPrice.setText(foodPrice)
     binding.tvnutrionalInfo.setText(foodDescription)
 }





}


