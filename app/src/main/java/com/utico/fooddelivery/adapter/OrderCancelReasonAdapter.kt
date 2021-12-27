package com.utico.fooddelivery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utico.fooddelivery.`interface`.CallbackOrderHistory
import com.utico.fooddelivery.databinding.ItemRowOrderCancelReasonBinding
import com.utico.fooddelivery.model.CancelReason

class OrderCancelReasonAdapter(val callbackOrderHistory: CallbackOrderHistory):RecyclerView.Adapter<OrderCancelReasonAdapter.MyViewHolder>(){
    var cancelist = mutableListOf<CancelReason>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater =LayoutInflater.from(parent.context)
        val  binding = ItemRowOrderCancelReasonBinding.inflate(inflater,parent,false)
         return   MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(cancelist[position])
        holder.radioButton.setOnClickListener {
            callbackOrderHistory.orderCancelReason(cancelist[position].reason)
        }
    }

    override fun getItemCount(): Int {
      return  cancelist.size
    }
    class MyViewHolder(binding: ItemRowOrderCancelReasonBinding):RecyclerView.ViewHolder(binding.root){
        val tvReason =binding.tvCancelReason
        val radioButton = binding.radioButton
      fun bind(data : CancelReason){
          tvReason.text = data.reason
      }
    }
}