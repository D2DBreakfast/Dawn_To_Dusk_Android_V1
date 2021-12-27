package com.utico.fooddelivery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utico.fooddelivery.databinding.ItemRowSubscriptionDetailsBinding
import com.utico.fooddelivery.model.upcomingMeals

class SubscriptionDetailsAdapter:RecyclerView.Adapter<SubscriptionDetailsAdapter.MyViewHolder>() {
    var upcomingMealsList = mutableListOf<upcomingMeals>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRowSubscriptionDetailsBinding.inflate(inflater,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(upcomingMealsList[position])
    }

    override fun getItemCount(): Int {
     return  upcomingMealsList.size
    }

    class MyViewHolder(val binding: ItemRowSubscriptionDetailsBinding):RecyclerView.ViewHolder(binding.root){
        val tvItemName = binding.tvItemName
        val tvDay = binding.tvDay
        val tvDescription = binding.tvDescription

      fun bind(data:upcomingMeals){
        tvItemName.text = data.meal
        tvDay.text = data.day
        tvDescription.text = data.description
      }
    }
}