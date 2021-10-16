package com.utico.fooddelivery.adapter

import android.app.Activity
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.utico.fooddelivery.R
import com.utico.fooddelivery.databinding.ItemRowCartBinding
import com.utico.fooddelivery.model.CartData
import com.utico.fooddelivery.model.Food

class AdapterCart : RecyclerView.Adapter<AdapterCart.MyViewHolder>() {
    var addToCartDetailsList = mutableListOf<CartData>()
    private var context: Context? = null


    //var addToCartItemCount =0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       context = parent.context
       val inflater = LayoutInflater.from(parent.context)
       val binding = ItemRowCartBinding.inflate(inflater,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(addToCartDetailsList[position])
        val tvAddToCartCount = holder.binding.tvAddToCartCount

        holder.binding.minusImageview.setOnClickListener {
           /* addToCartItemCount--
            if (addToCartItemCount > 0){
                tvAddToCartCount.text = addToCartItemCount.toString()
            }*/
           // holder.bind(foodlist[position],addToCartItemCount)

        }

        holder.binding.plusImageview.setOnClickListener {
           /* addToCartItemCount++
           // holder.bind(foodlist[position],addToCartItemCount)\
            if (addToCartItemCount > 10){
                tvAddToCartCount.text = addToCartItemCount.toString()
            }*/

        }

        val rltCart = holder.binding.rltCart
            rltCart.setOnClickListener {
                val sharedPreferences = context?.getSharedPreferences(context?.resources?.getString(R.string.cart_details_sharedPreferences),MODE_PRIVATE)
                val editor = sharedPreferences?.edit()
                editor?.putString("itemMainCategoryName",holder.tvItemMainCategoryName.text.toString())
                editor?.putString("itemSubCategoryName",holder.tvItemSubCategoryName.text.toString())
                editor?.putString("itemFoodType",holder.tvItemFoodType.text.toString())
                editor?.putString("itemName",holder.tvItemName.text.toString())
                editor?.putString("itemId",holder.tvItemId.text.toString())
                editor?.putString("itemQuantity",holder.tvItemQuantity.text.toString())
                editor?.putString("itemPrice",holder.tvDescription.text.toString())
                editor?.putString("userId",holder.tvItemUserId.text.toString())
                editor?.commit()
                holder.rltCart.setBackgroundColor(context!!.resources.getColor(R.color.green))


            }

    }

    override fun getItemCount(): Int {
        return addToCartDetailsList.size

    }

    class MyViewHolder(val binding: ItemRowCartBinding): RecyclerView.ViewHolder(binding.root){
        val tvItemMainCategoryName = binding.itemMainCategoryName
        val tvItemSubCategoryName  = binding.tvItemSubCategoryName
        val tvItemFoodType = binding.itemFoodType
        val tvItemName = binding.tvFoodTitle
        val tvItemId = binding.tvItemId
        val tvItemQuantity = binding.itemQuantity
        val tvItemPrice = binding.tvPrice
        val tvItemUserId = binding.tvUserId

        val imageView = binding.minusImageview
        val tvAddToCartCount = binding.tvAddToCartCount
        val tvDescription = binding.tvFoodDesc
        val tvSelectedDate = binding.tvSelectDate
        val rltCart = binding.rltCart


        fun bind(data: CartData){
            tvItemMainCategoryName.text = data.itemMainCategoryName
            tvItemSubCategoryName.text = data.itemSubCategoryName
            tvItemName.text = data.itemName
            tvItemId.text = data.itemId
            tvItemQuantity.text = data.itemQuantity
            tvItemPrice.text =data.itemPrice
            tvItemUserId.text = data.userId
            tvDescription.text = data.orderStatus
            tvSelectedDate.text = data.itemId

           /* val image_url = data.
            Picasso.get()
                .load(image_url)
                .into(imageView)*/
        }
    }

}