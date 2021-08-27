package com.utico.fooddelivery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.utico.fooddelivery.databinding.ItemRowGlobalSearchBinding
import com.utico.fooddelivery.model.Food
import com.utico.fooddelivery.model.FoodList

class AdapterGlobalSearch : RecyclerView.Adapter<AdapterGlobalSearch.MyViewHolder>() {
    var foodlist = mutableListOf<Food>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val inflater = LayoutInflater.from(parent.context)
       val binding = ItemRowGlobalSearchBinding.inflate(inflater,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(foodlist[position])

    }

    override fun getItemCount(): Int {
        return foodlist.size

    }

    class MyViewHolder(val binding: ItemRowGlobalSearchBinding): RecyclerView.ViewHolder(binding.root){
        val tvName = binding.tvTitle
        val tvDescription = binding.tvDescription
        val tvPrice = binding.tvPrice
        val imageView = binding.imageView

        fun bind(data : Food ){
            tvName.text = data.id.toString()
            tvDescription.text = data.email
            tvPrice.text = data.first_name
            val image_url = data.avatar
            Picasso.get()
                .load(image_url)
                .into(imageView)
        }
    }

}