package com.utico.fooddelivery.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utico.fooddelivery.R
import com.utico.fooddelivery.`interface`.CallbackOrderHistory
import com.utico.fooddelivery.databinding.ItemRowOrderHistoryBinding
import com.utico.fooddelivery.model.PlacedOrders

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
        }
        holder.bind(orderHistoryList[position],context)
        val btnCancel = holder.binding.btnCancel

           if (orderHistoryList[position].deliveryStatus.equals("Delivered")||orderHistoryList[position].deliveryStatus.equals("Cancelled")){
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
        val relativeLayout = binding.relativeLayout
        val tvOrderStatus = binding.tvStatus
        val tvOrderDate = binding.tvOrderDate
        val tvPlanEndDate  = binding.tvPlanEndDate
        val tvOrderNumber = binding.tvOrderNumber
        val tvCategoryName = binding.tvCategoryName
        val tvPrice = binding.tvPrice
        val tvSubscriptionPlan = binding.tvSubscriptionPlan

        fun bind(placeOrderHistoryData: PlacedOrders, context: Context?){
            if (placeOrderHistoryData.orderStatus.equals("Received")){
                tvOrderStatus.setTextColor(context!!.resources.getColor(R.color.gold))
            }else if (placeOrderHistoryData.orderStatus.equals("Cancelled")){
                tvOrderStatus.setTextColor(context!!.resources.getColor(R.color.red))
            }else if (placeOrderHistoryData.orderStatus.equals("Delivered")){
                tvOrderStatus.setTextColor(context!!.resources.getColor(R.color.green))
            }
            if (placeOrderHistoryData.categoryType.equals("Subscription")){
                tvOrderNumber.text ="Order Number"+placeOrderHistoryData.orderId
                tvPrice.text = "AED"+" "+placeOrderHistoryData.paymentPaid
                tvOrderStatus.text = placeOrderHistoryData.orderStatus
                tvCategoryName.text =placeOrderHistoryData.categoryType+" "+"Plan"
                tvSubscriptionPlan.text = placeOrderHistoryData.title
                tvOrderDate.text ="Your Plan Start on"+" "+placeOrderHistoryData.orderDate
                tvPlanEndDate.text = "Your Plan End on "+" " +placeOrderHistoryData.endDate
            }else{
                tvCategoryName.visibility = View.GONE
                tvSubscriptionPlan.visibility = View.GONE
                tvPlanEndDate.visibility = View.GONE
                tvOrderStatus.text = placeOrderHistoryData.orderStatus
                tvOrderDate.text ="Your Delivery Order on"+" "+placeOrderHistoryData.orderDate
                tvOrderNumber.text ="Order Number"+placeOrderHistoryData.orderId
                tvPrice.text = "AED"+" "+placeOrderHistoryData.paymentPaid

            }

        }
    }
}