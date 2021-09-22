package com.utico.fooddelivery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utico.fooddelivery.databinding.ItemRowUpcomingMealsBinding

class AdapterUpcomingMeals : RecyclerView.Adapter<AdapterUpcomingMeals.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRowUpcomingMealsBinding.inflate(inflater,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return 10
    }

    class MyViewHolder(val binding: ItemRowUpcomingMealsBinding) : RecyclerView.ViewHolder(binding.root)
}