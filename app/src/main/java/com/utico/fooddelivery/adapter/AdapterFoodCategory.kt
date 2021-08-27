package com.utico.fooddelivery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utico.fooddelivery.databinding.ItemRowFoodCategoryNameBinding
import com.utico.fooddelivery.model.FoodCategory

class AdapterFoodCategory: RecyclerView.Adapter<AdapterFoodCategory.FoodCategoryViewHolder>() {
  var foodCategoryName = mutableListOf<FoodCategory>()

    fun setFoodCategoryNameList(foodCategoryName: List<FoodCategory>){
       this.foodCategoryName = foodCategoryName.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodCategoryViewHolder {
      val inflater = LayoutInflater.from(parent.context)
      val binding = ItemRowFoodCategoryNameBinding.inflate(inflater,parent,false)
        return FoodCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodCategoryViewHolder, position: Int) {
       /* val foodCategoryname = foodCategoryName[position]
        holder.binding.name.text = foodCategoryname.FoodCategoryName*/
      val tvCategoryName = holder.binding.tvCategoryName
       tvCategoryName.setOnClickListener {
         print("your click Item Category is Egg")
       }

    }

    override fun getItemCount(): Int {
        return 30
    }

    class FoodCategoryViewHolder(val binding: ItemRowFoodCategoryNameBinding) : RecyclerView.ViewHolder(binding.root)


}