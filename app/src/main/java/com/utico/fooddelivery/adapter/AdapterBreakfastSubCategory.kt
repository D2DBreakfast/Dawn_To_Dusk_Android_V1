package com.utico.fooddelivery.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.utico.fooddelivery.R
import com.utico.fooddelivery.`interface`.BreakfastSubcategoryMenuDetailsListener
import com.utico.fooddelivery.databinding.ItemRowBreakfastSubCategoryBinding
import com.utico.fooddelivery.model.SubCategoryData

class AdapterBreakfastSubCategory(val breakfastSubcategoryMenuDetailsListener: BreakfastSubcategoryMenuDetailsListener ): RecyclerView.Adapter<AdapterBreakfastSubCategory.FoodCategoryViewHolder>() {
    var breakfastCategoryList = mutableListOf<SubCategoryData>()
    var context: Context? = null
    var breakfastSubCategoryName:String? = null
    var isCheck:Boolean? = true
    var adapterFirstPosition: Int? = 0
    var adapterBackgroundSetPosition:Int?=0
/*
    fun setFoodSubCategoryName(foodSubCategoryName: List<SubCategoryResponseModel>){
       this.subCategoryList = foodSubCategoryName.toMutableList()
        notifyDataSetChanged()
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodCategoryViewHolder {
      val inflater = LayoutInflater.from(parent.context)
        context = parent.context
      val binding = ItemRowBreakfastSubCategoryBinding.inflate(inflater,parent,false)
        return FoodCategoryViewHolder(binding)

    }

    override fun onBindViewHolder(holder: FoodCategoryViewHolder, position: Int) {
        adapterFirstPosition = holder?.adapterPosition
        holder.bind(breakfastCategoryList[position],context!!,adapterFirstPosition!!)
           holder.tv_name.setOnClickListener {
               if (isCheck == true){
                   //Toast.makeText(context,holder.tv_name.text, Toast.LENGTH_SHORT).show()
                   breakfastSubCategoryName = holder.tv_name.text.toString()
                   breakfastSubcategoryMenuDetailsListener.getBreakfastSubCategoryRelatedMenuDetails(breakfastSubCategoryName!!,isCheck!!)
                   holder.cardView.setBackgroundColor(context!!.resources.getColor(R.color.green))
                   holder.tv_name.setTextColor(context!!.resources.getColor(R.color.white))
                   Toast.makeText(context,isCheck.toString(), Toast.LENGTH_SHORT).show()
                   isCheck = false
               }else{
                   breakfastSubCategoryName = holder.tv_name.text.toString()
                   breakfastSubcategoryMenuDetailsListener.getBreakfastSubCategoryRelatedMenuDetails(breakfastSubCategoryName!!,isCheck!!)
                   holder.cardView.setBackgroundColor(context!!.resources.getColor(R.color.white))
                   holder.tv_name.setTextColor(context!!.resources.getColor(R.color.grey_dark))
                   Toast.makeText(context,isCheck.toString(), Toast.LENGTH_SHORT).show()
                   isCheck = true
               }
              /* val itemPosition= breakfastCategoryList[position]._id.toInt()
               if (itemPosition==adapterFirstPosition){
                   holder.cardView.setBackgroundColor(context!!.resources.getColor(R.color.green))
                   holder.tv_name.setTextColor(context!!.resources.getColor(R.color.white))
               }else{
                   holder.cardView.setBackgroundColor(context!!.resources.getColor(R.color.white))
                   holder.tv_name.setTextColor(context!!.resources.getColor(R.color.grey_dark))
               }*/
           }
    }


    override fun getItemCount(): Int {
        return breakfastCategoryList.size
    }

    class FoodCategoryViewHolder(val binding: ItemRowBreakfastSubCategoryBinding) : RecyclerView.ViewHolder(binding.root){
      val tv_name = binding.tvCategoryName
      val cardView = binding.cardview

      fun bind(subCategoryData: SubCategoryData, context: Context, holderPosition: Int){
        tv_name.text = subCategoryData.subCategoryName
          if (holderPosition == 0) {
              cardView.setBackgroundColor(context!!.resources.getColor(R.color.green))
              tv_name.setTextColor(context!!.resources.getColor(R.color.white))
          }
      }
    }

}

/*
https://www.codevscolor.com/kotlin-android-adapter-callback-using-lambda-function*/
