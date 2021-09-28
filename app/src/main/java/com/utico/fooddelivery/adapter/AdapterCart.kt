package com.utico.fooddelivery.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.utico.fooddelivery.databinding.ItemRowCartBinding
import com.utico.fooddelivery.model.Food

class AdapterCart : RecyclerView.Adapter<AdapterCart.MyViewHolder>() {
    var foodlist = mutableListOf<Food>()

    var addToCartItemCount =0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val inflater = LayoutInflater.from(parent.context)
       val binding = ItemRowCartBinding.inflate(inflater,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       // holder.bind(foodlist[position],addToCartItemCount)
        val tvAddToCartCount = holder.binding.tvAddToCartCount

        holder.binding.minusImageview.setOnClickListener {
            addToCartItemCount--
            if (addToCartItemCount > 0){
                tvAddToCartCount.text = addToCartItemCount.toString()
            }
           // holder.bind(foodlist[position],addToCartItemCount)

        }

        holder.binding.plusImageview.setOnClickListener {
            addToCartItemCount++
           // holder.bind(foodlist[position],addToCartItemCount)\
            if (addToCartItemCount > 10){
                tvAddToCartCount.text = addToCartItemCount.toString()
            }

        }

    }

    override fun getItemCount(): Int {
        return foodlist.size

    }

    class MyViewHolder(val binding: ItemRowCartBinding): RecyclerView.ViewHolder(binding.root){
        val imageView = binding.minusImageview
        val tvAddToCartCount = binding.tvAddToCartCount

        fun bind(data : Food,addToCartCount:Int){

            if(addToCartCount > 0){
                tvAddToCartCount.text = addToCartCount.toString()
            }

            if (addToCartCount <= 10){
                tvAddToCartCount.text = addToCartCount.toString()

            }
            /*tvName.text = data.id.toString()
            tvDescription.text = data.email
            tvPrice.text = data.first_name
            val image_url = data.avatar
            Picasso.get()
                .load(image_url)
                .into(imageView)*/
        }
    }

}