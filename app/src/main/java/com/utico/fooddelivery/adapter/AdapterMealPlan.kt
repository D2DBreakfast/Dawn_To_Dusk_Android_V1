package com.utico.fooddelivery.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utico.fooddelivery.databinding.ItemRowMealPlanBinding
import java.util.zip.Inflater

class AdapterMealPlan:RecyclerView.Adapter<AdapterMealPlan.MyviewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
      val inflater = LayoutInflater.from(parent.context)
      val binding = ItemRowMealPlanBinding.inflate(inflater,parent,false)
      return MyviewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return 20

    }

    class MyviewHolder(val binding: ItemRowMealPlanBinding) :RecyclerView.ViewHolder(binding.root)
}