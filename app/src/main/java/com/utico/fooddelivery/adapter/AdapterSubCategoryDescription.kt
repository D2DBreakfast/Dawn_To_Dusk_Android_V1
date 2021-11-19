package com.utico.fooddelivery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.utico.fooddelivery.R
import com.utico.fooddelivery.databinding.ItemRowSubcategoryDescriptionBinding
import com.utico.fooddelivery.model.DataX

class AdapterSubCategoryDescription :RecyclerView.Adapter<AdapterSubCategoryDescription.MyViewHolder>(){
     var menuList = mutableListOf<DataX>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRowSubcategoryDescriptionBinding.inflate(inflater,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
         holder.bind(menuList[position])
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    class MyViewHolder(val binding: ItemRowSubcategoryDescriptionBinding):RecyclerView.ViewHolder(binding.root){
        val tvTitle = binding.tvTitle
        val tvDescription =binding.tvDescription
        val tvPrice = binding.tvPrice
        val vegNonVegImageview = binding.vegOrNonVegImageView

        fun bind(data:DataX){
         tvTitle.text = data.itemName
         tvDescription.text = data.itemDescription
         tvPrice.text = "AED"+" "+data.itemPrice
           if (data.itemFoodType.equals("Veg")) {
              vegNonVegImageview.setBackgroundResource(R.drawable.veg)
           }else{
               vegNonVegImageview.setBackgroundResource(R.drawable.non_veg)
           }

       }

    }
}