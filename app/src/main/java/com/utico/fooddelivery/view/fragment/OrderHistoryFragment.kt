package com.utico.fooddelivery.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.utico.fooddelivery.R
import com.utico.fooddelivery.`interface`.CallbackOrderHistory
import com.utico.fooddelivery.adapter.OrderCancelReasonAdapter
import com.utico.fooddelivery.adapter.OrderHistoryAdapter
import com.utico.fooddelivery.databinding.OrderHistoryFragmentBinding
import com.utico.fooddelivery.model.OrderCancelReasonResponseModel
import com.utico.fooddelivery.model.OrderCancelResponse
import com.utico.fooddelivery.model.PlacedOrderHistoryResponse
import com.utico.fooddelivery.viewmodel.OrderHistoryViewModel

class OrderHistoryFragment : Fragment(),CallbackOrderHistory {
    private lateinit var binding: OrderHistoryFragmentBinding
    private lateinit var viewModel: OrderHistoryViewModel
    private lateinit var orderHistoryAdapter: OrderHistoryAdapter
    private lateinit var orderCancelReasonAdapter: OrderCancelReasonAdapter
    private var cancelUserId:String? = null
    private var cancelOrderId:String? = null
    private var orderCancelReason:String? = null


    companion object {
        fun newInstance() = OrderHistoryFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(OrderHistoryViewModel::class.java)
        binding = OrderHistoryFragmentBinding.inflate(inflater,container,false)

        val view:View = binding.root
        setUpAdapter()
        getOrderHistoryData()
        return view
    }


    fun setUpAdapter(){
        val recyclerView = binding.orderHistoryRecyclerView
            recyclerView.layoutManager = LinearLayoutManager(activity)
             orderHistoryAdapter = OrderHistoryAdapter(this)
             recyclerView.adapter =orderHistoryAdapter
    }

    private fun getOrderHistoryData(){
        viewModel.orderHistoryObservable().observe(viewLifecycleOwner, Observer<PlacedOrderHistoryResponse> {
            if (it.statusCode == 400){
                Toast.makeText(context,it.message,Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(context,it.message,Toast.LENGTH_LONG).show()
                orderHistoryAdapter.orderHistoryList = it.PlacedOrdersList.toMutableList()
             orderHistoryAdapter.notifyDataSetChanged()
            }
        })
        viewModel.apiCallOrderHistory("9")
    }



    override fun orderCancelDialog() {
        bottomAlertDialog()
    }

    override fun placedOrderCancel(userId: String, orderId: String) {
        cancelUserId =userId
        cancelOrderId = orderId
    }

    override fun orderCancelReason(reason: String) {
        orderCancelReason = reason
    }

    private fun bottomAlertDialog(){
        val bottomSheetForOrderCancelationReasonLayout = layoutInflater.inflate(R.layout.order_cancel_dialogsheet,null)
        val cancelReasonRecyclerView = bottomSheetForOrderCancelationReasonLayout.findViewById<RecyclerView>(R.id.cancelReasonRecyclerView)
        val btnSubmit = bottomSheetForOrderCancelationReasonLayout.findViewById<Button>(R.id.btnSubmit)
        val bottomSheetDialog = BottomSheetDialog(activity as AppCompatActivity)
        bottomSheetDialog.setContentView(bottomSheetForOrderCancelationReasonLayout)
        bottomSheetDialog.show()
            orderCancelReasonAdapter= OrderCancelReasonAdapter(this)
            cancelReasonRecyclerView.layoutManager = LinearLayoutManager(activity)
            cancelReasonRecyclerView.adapter = orderCancelReasonAdapter
            viewModel.cancelReasonObservable().observe(viewLifecycleOwner, Observer<OrderCancelReasonResponseModel> {
                orderCancelReasonAdapter.cancelist = it.cancelReasons.toMutableList()
                orderCancelReasonAdapter.notifyDataSetChanged()
            })
            viewModel.apiCallForOrderCancelReason()

        btnSubmit.setOnClickListener {
           viewModel.placedOrderCancelObservable().observe(viewLifecycleOwner, Observer<OrderCancelResponse> {
               Toast.makeText(context,it.message,Toast.LENGTH_SHORT).show()
               bottomSheetDialog.dismiss()
               getOrderHistoryData()
               activity?.finish()
           })
          viewModel.apiCallForOrderCancel(cancelUserId!!,cancelOrderId!!,orderCancelReason!!)
        }

    }

}