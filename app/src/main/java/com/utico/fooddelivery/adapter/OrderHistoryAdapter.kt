package com.utico.fooddelivery.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.utico.fooddelivery.R
import com.utico.fooddelivery.`interface`.CallbackOrderHistory
import com.utico.fooddelivery.databinding.ItemRowOrderHistoryBinding
import com.utico.fooddelivery.model.PlacedOrders
import okhttp3.internal.addHeaderLenient

class OrderHistoryAdapter(val callbackOrderHistory: CallbackOrderHistory) : RecyclerView.Adapter<OrderHistoryAdapter.MyViewHolder>() {
    var orderHistoryList = mutableListOf<PlacedOrders>()
    var itemQuantityWithName:String? = null
    var context:Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
      val inflater = LayoutInflater.from(parent.context)
      val binding = ItemRowOrderHistoryBinding.inflate(inflater,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        for (data in orderHistoryList){
            itemQuantityWithName =data.orderDetails[position].itemQuantity+ "x" + data.orderDetails[position].itemName
        }
        holder.bind(orderHistoryList[position],itemQuantityWithName!!)
        val btnCancel = holder.binding.btnCancel

           if (orderHistoryList[position].deliveryStatus.equals("delivered")||orderHistoryList[position].deliveryStatus.equals("Cancelled")){
               btnCancel.visibility = View.GONE
           }else{
               btnCancel.setOnClickListener {
                   callbackOrderHistory.orderCancelDialog()
                   callbackOrderHistory.placedOrderCancel(orderHistoryList[position].userId,orderHistoryList[position].orderId)

               }
           }
    }

    override fun getItemCount(): Int {
     return orderHistoryList.size
    }

    class MyViewHolder(val binding: ItemRowOrderHistoryBinding) :RecyclerView.ViewHolder(binding.root){
        val tvOrderStatus = binding.tvStatus
        val tvOrderDate = binding.tvOrderDate
        val tvOrderNumber = binding.tvOrderNumber
        val tvQuantityWithItemName = binding.tvQuantityWithItemName
        val tvPrice = binding.tvPrice


        fun bind(placeOrderHistoryData: PlacedOrders,itemNameWithQuantity:String){
            tvOrderStatus.text = placeOrderHistoryData.orderStatus
           tvOrderDate.text ="Your Delivery order on"+" "+placeOrderHistoryData.orderDate
           tvOrderNumber.text ="Order Number"+placeOrderHistoryData.orderId
           tvQuantityWithItemName.text = placeOrderHistoryData.orderDetails[adapterPosition].itemQuantity+"x"+placeOrderHistoryData.orderDetails[adapterPosition].itemName
            tvPrice.text = "AED"+" "+placeOrderHistoryData.orderDetails[adapterPosition].itemPrice

        }
    }
}