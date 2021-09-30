package com.utico.fooddelivery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utico.fooddelivery.databinding.ItemRowCouponsBinding

class AdapterCoupons : RecyclerView.Adapter<AdapterCoupons.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      val inflater = LayoutInflater.from(parent.context)
      val binding = ItemRowCouponsBinding.inflate(inflater,parent,false)
        return MyViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
     return 10
    }

    class MyViewHolder(val binding: ItemRowCouponsBinding) : RecyclerView.ViewHolder(binding.root)
}