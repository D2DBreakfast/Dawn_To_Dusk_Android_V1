package com.utico.fooddelivery.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utico.fooddelivery.R
import com.utico.fooddelivery.`interface`.FoodSubCategoryListener
import com.utico.fooddelivery.databinding.ItemRowBrunchSubCategoryBinding
import com.utico.fooddelivery.model.FoodSubCategory

class AdapterBrunchSubCategory: RecyclerView.Adapter<AdapterBrunchSubCategory.FoodCategoryViewHolder>() {
  var foodSubCategoryList = mutableListOf<FoodSubCategory>()
  var context: Context? = null
  var foodCategoryName:String? = null
  val foodSubCategoryListener:FoodSubCategoryListener? = null
  var isCheck:Boolean? = true



  fun setFoodSubCategoryName(foodSubCategoryName: List<FoodSubCategory>){
       this.foodSubCategoryList = foodSubCategoryName.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodCategoryViewHolder {
      val inflater = LayoutInflater.from(parent.context)
        context = parent.context
        val binding = ItemRowBrunchSubCategoryBinding.inflate(inflater,parent,false)
        return FoodCategoryViewHolder(binding)

    }

    override fun onBindViewHolder(holder: FoodCategoryViewHolder, position: Int) {
        //holder.bind(foodSubCategoryList[position])
        holder.tv_name.setOnClickListener {
          if (isCheck == true){
            //Toast.makeText(context,holder.tv_name.text, Toast.LENGTH_SHORT).show()
            foodCategoryName = holder.tv_name.text.toString()
            foodSubCategoryListener?.getFoodSubCategoryName(foodCategoryName!!)
            holder.cardView.setBackgroundColor(context!!.resources.getColor(R.color.green))
            holder.tv_name.setTextColor(context!!.resources.getColor(R.color.white))
            isCheck = false
          }else{
            foodCategoryName = holder.tv_name.text.toString()
            foodSubCategoryListener?.getFoodSubCategoryName(foodCategoryName!!)
            holder.cardView.setBackgroundColor(context!!.resources.getColor(R.color.white))
            holder.tv_name.setTextColor(context!!.resources.getColor(R.color.grey_dark))
            isCheck = true

          }


        }

    }

    override fun getItemCount(): Int {
        return 10
    }

    class FoodCategoryViewHolder(val binding: ItemRowBrunchSubCategoryBinding) : RecyclerView.ViewHolder(binding.root){
      val tv_name = binding.tvCategoryName
      val cardView = binding.cardview

      fun bind(data:FoodSubCategory){
        tv_name.text = data.first_name

      }
    }

}

/*
https://www.codevscolor.com/kotlin-android-adapter-callback-using-lambda-function*/
