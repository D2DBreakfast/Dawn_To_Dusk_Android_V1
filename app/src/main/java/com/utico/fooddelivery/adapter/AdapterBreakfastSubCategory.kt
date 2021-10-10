package com.utico.fooddelivery.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utico.fooddelivery.R
import com.utico.fooddelivery.`interface`.FoodSubCategoryListener
import com.utico.fooddelivery.databinding.ItemRowBreakfastSubCategoryBinding
import com.utico.fooddelivery.model.FoodSubCategory

class AdapterBreakfastSubCategory(val foodSubCategoryListener:FoodSubCategoryListener): RecyclerView.Adapter<AdapterBreakfastSubCategory.FoodCategoryViewHolder>() {
  var foodSubCategoryList = mutableListOf<FoodSubCategory>()
  var context: Context? = null
  var foodCategoryName:String? = null



  fun setFoodSubCategoryName(foodSubCategoryName: List<FoodSubCategory>){
       this.foodSubCategoryList = foodSubCategoryName.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodCategoryViewHolder {
      val inflater = LayoutInflater.from(parent.context)
        context = parent.context
      val binding = ItemRowBreakfastSubCategoryBinding.inflate(inflater,parent,false)
        return FoodCategoryViewHolder(binding)

    }

    override fun onBindViewHolder(holder: FoodCategoryViewHolder, position: Int) {
        //holder.bind(foodSubCategoryList[position])
        holder.tv_name.setOnClickListener {
          //Toast.makeText(context,holder.tv_name.text, Toast.LENGTH_SHORT).show()
            foodCategoryName = holder.tv_name.text.toString()
            foodSubCategoryListener.getFoodSubCategoryName(foodCategoryName!!)
            holder.cardView.setBackgroundColor(context!!.resources.getColor(R.color.green))
            holder.tv_name.setTextColor(context!!.resources.getColor(R.color.white))

        }

    }

    override fun getItemCount(): Int {
        return 10
    }

    class FoodCategoryViewHolder(val binding: ItemRowBreakfastSubCategoryBinding) : RecyclerView.ViewHolder(binding.root){
      val tv_name = binding.tvCategoryName
      val cardView = binding.cardview

      fun bind(data:FoodSubCategory){
        tv_name.text = data.first_name

      }
    }

}

/*
https://www.codevscolor.com/kotlin-android-adapter-callback-using-lambda-function*/
