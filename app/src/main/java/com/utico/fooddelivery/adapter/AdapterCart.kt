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
import com.utico.fooddelivery.`interface`.CallbackAddToCartDetails
import com.utico.fooddelivery.databinding.ItemRowCartBinding
import com.utico.fooddelivery.model.CartData
import com.utico.fooddelivery.model.Food

class AdapterCart(val callbackAddToCartDetails: CallbackAddToCartDetails): RecyclerView.Adapter<AdapterCart.MyViewHolder>() {
    var addToCartDetailsList = mutableListOf<CartData>()
    private var context: Context? = null
    private var itemQuantityCountPlus: Int = 0
    private  var itemPriceCountPlus:Int = 0
    private var itemQuantityCountMinus: Int = 0
    private var itemPriceCountMinus:Int = 0
    private var isCheck:Boolean? = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       context = parent.context
       val inflater = LayoutInflater.from(parent.context)
       val binding = ItemRowCartBinding.inflate(inflater,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(addToCartDetailsList[position],callbackAddToCartDetails)
        holder.binding.plusImageview.setOnClickListener {
                 var itemQuantity = holder.tvQuantity.text.toString()
                 itemQuantityCountPlus = itemQuantity.toInt()
                 itemQuantityCountPlus++
                 holder.tvQuantity.setText(itemQuantityCountPlus.toString())
                 var itemPrice = holder.tvItemPrice.text.toString()
                 itemPriceCountPlus += itemPrice.toInt()
                 holder.tvItemPrice.setText(itemPriceCountPlus.toString())

        }

        holder.binding.minusImageview.setOnClickListener {
                    var itemQuantity = holder.tvQuantity.text.toString()
                    itemQuantityCountMinus = itemQuantity.toInt()
                    itemQuantityCountMinus--
                    holder.tvQuantity.setText(itemQuantityCountMinus.toString())
                    var itemPrice = holder.tvItemPrice.text.toString()
                    itemPriceCountMinus -= itemPrice.toInt()
                    holder.tvItemPrice.setText(itemPriceCountMinus.toString())

        }



       /* val rltCart = holder.binding.rltCart
            rltCart.setOnClickListener {
                val sharedPreferences = context?.getSharedPreferences(context?.resources?.getString(R.string.cart_details_sharedPreferences),MODE_PRIVATE)
                val editor = sharedPreferences?.edit()
                editor?.putString("itemMainCategoryName",holder.tvItemMainCategoryName.text.toString())
                editor?.putString("itemSubCategoryName",holder.tvItemSubCategoryName.text.toString())
                editor?.putString("itemFoodType",holder.tvItemFoodType.text.toString())
                editor?.putString("itemName",holder.tvItemName.text.toString())
                editor?.putString("itemId",holder.tvItemId.text.toString())
                editor?.putString("itemQuantity",holder.tvQuantity.text.toString())
                editor?.putString("itemPrice",holder.tvDescription.text.toString())
                editor?.putString("userId",holder.tvItemUserId.text.toString())
                editor?.commit()
                holder.rltCart.setBackgroundColor(context!!.resources.getColor(R.color.light_green))


            }*/

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
        val tvItemPrice = binding.tvPrice
        val tvItemUserId = binding.tvUserId
        val tvQuantity = binding.tvQuantity


        val imageView = binding.minusImageview
        val tvDescription = binding.tvFoodDesc
        val tvSelectedDate = binding.tvSelectDate
        val rltCart = binding.rltCart


        fun bind(data: CartData,callbackAddToCartDetails: CallbackAddToCartDetails){
            tvItemMainCategoryName.text = data.itemMainCategoryName
            tvItemSubCategoryName.text = data.itemSubCategoryName
            tvItemName.text = data.itemName
            tvItemId.text = data.itemId
            tvItemPrice.text =data.itemPrice
            tvItemUserId.text = data.userId
            tvDescription.text = data.orderStatus
            tvSelectedDate.text = data.itemId
            tvQuantity.text = data.itemQuantity
            callbackAddToCartDetails.passAddToCartDetails(data.itemMainCategoryName,data.itemSubCategoryName,
            data.itemName,data.itemId,data.itemQuantity,data.itemPrice)
           /* val image_url = data.
            Picasso.get()
                .load(image_url)
                .into(imageView)*/
        }
    }

}