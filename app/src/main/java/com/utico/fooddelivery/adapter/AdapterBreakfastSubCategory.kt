package com.utico.fooddelivery.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.utico.fooddelivery.R
import com.utico.fooddelivery.`interface`.CallbackBreakfastSubCategoryName
import com.utico.fooddelivery.databinding.ItemRowBreakfastSubCategoryBinding
import com.utico.fooddelivery.model.SubCategoryData

class AdapterBreakfastSubCategory(val callbackBreakfastSubCategoryName: CallbackBreakfastSubCategoryName ): RecyclerView.Adapter<AdapterBreakfastSubCategory.FoodCategoryViewHolder>() {
    var breakfastSubCategoryList = mutableListOf<SubCategoryData>()
    var context: Context? = null
    var breakfastSubCategoryName:String? = null
    var isCheck:Boolean? = true
    var adapterFirstPosition: Int? = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodCategoryViewHolder {
      val inflater = LayoutInflater.from(parent.context)
        context = parent.context
      val binding = ItemRowBreakfastSubCategoryBinding.inflate(inflater,parent,false)
        return FoodCategoryViewHolder(binding)

    }

    override fun onBindViewHolder(holder: FoodCategoryViewHolder, position: Int) {
        adapterFirstPosition = holder?.adapterPosition
        holder.bind(breakfastSubCategoryList[position],context!!,adapterFirstPosition!!)
           holder.tv_name.setOnClickListener {
               if (breakfastSubCategoryList[position].isSelected.equals("false")){
                   //Toast.makeText(context,holder.tv_name.text, Toast.LENGTH_SHORT).show()
                  /* breakfastSubCategoryName = holder.tv_name.text.toString()
                   callbackBreakfastSubCategoryName.getBreakfastSubCategoryRelatedMenuDetails(breakfastSubCategoryName!!,isCheck!!)
                   holder.cardView.setBackgroundColor(context!!.resources.getColor(R.color.green))
                   holder.tv_name.setTextColor(context!!.resources.getColor(R.color.white))
                   Toast.makeText(context,isCheck.toString(), Toast.LENGTH_SHORT).show()*/
                   val sunCategoryName = breakfastSubCategoryList[position].subCategoryName
                        callbackBreakfastSubCategoryName.getBreakfastSubCategoryName(sunCategoryName,isCheck!!)
                        breakfastSubCategoryList[position].isSelected ="true"
                        holder.cardView.setBackgroundColor(context!!.resources.getColor(R.color.green))
                        holder.tv_name.setTextColor(context!!.resources.getColor(R.color.white))
                        notifyItemChanged(position)
               }else{
                  /* val sunCategoryName = breakfastSubCategoryList[position].subCategoryName
                   callbackBreakfastSubCategoryName.getBreakfastSubCategoryName(sunCategoryName,isCheck!!)
                   holder.cardView.setBackgroundColor(context!!.resources.getColor(R.color.white))
                   holder.tv_name.setTextColor(context!!.resources.getColor(R.color.grey_dark))
                   Toast.makeText(context,isCheck.toString(), Toast.LENGTH_SHORT).show()
                   isCheck = true*/
               }
           }
    }


    override fun getItemCount(): Int {
        return breakfastSubCategoryList.size
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
          if (subCategoryData.isSelected.equals("true")){
              cardView.setBackgroundColor(context!!.resources.getColor(R.color.white))
              tv_name.setTextColor(context!!.resources.getColor(R.color.grey_dark))
          }
      }
    }

}

/*
https://www.codevscolor.com/kotlin-android-adapter-callback-using-lambda-function*/
