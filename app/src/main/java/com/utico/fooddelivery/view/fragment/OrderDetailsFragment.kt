package com.utico.fooddelivery.view.fragment

import android.app.DatePickerDialog
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.utico.fooddelivery.R
import com.utico.fooddelivery.databinding.CustomToolbarBinding
import com.utico.fooddelivery.databinding.OrderDetailsFragmentBinding
import com.utico.fooddelivery.viewmodel.OrderDetailsViewModel
import java.util.*

class OrderDetailsFragment : Fragment() {

  private lateinit var binding: OrderDetailsFragmentBinding

    companion object {
        fun newInstance() = OrderDetailsFragment()
    }

    private lateinit var viewModel: OrderDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
/*
        val view = inflater.inflate(R.layout.order_details_fragment, container, false)
*/
            binding = OrderDetailsFragmentBinding.inflate(inflater,container,false)
            val view:View = binding.root

           datePicker()
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


}