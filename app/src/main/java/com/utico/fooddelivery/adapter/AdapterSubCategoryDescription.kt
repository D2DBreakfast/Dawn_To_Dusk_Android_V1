package com.utico.fooddelivery.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.utico.fooddelivery.R
import com.utico.fooddelivery.`interface`.CallbackSubCategoryDescription
import com.utico.fooddelivery.databinding.ItemRowSubcategoryDescriptionBinding
import com.utico.fooddelivery.model.SubCategoryMenuData
import com.utico.fooddelivery.view.LoginActivity

class AdapterSubCategoryDescription(var callbackSubCategoryDescription: CallbackSubCategoryDescription) :RecyclerView.Adapter<AdapterSubCategoryDescription.MyViewHolder>(){
     var menuList = mutableListOf<SubCategoryMenuData>()
     var context:Context? = null
     var userId:String? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
           context=parent.context
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRowSubcategoryDescriptionBinding.inflate(inflater,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
         holder.bind(menuList[position])
         holder.binding.relativeLayout.setOnClickListener {
             val registrationSharedPreferences = context?.getSharedPreferences(context?.resources?.getString(R.string.registration_details_sharedPreferences), Context.MODE_PRIVATE)
                   userId = registrationSharedPreferences?.getString("userId", "")
             if (userId.equals("") || userId.equals(null)) {
                 Toast.makeText(context, "Before Add to Cart Please Register!!", Toast.LENGTH_LONG).show()
                 val intent = Intent(context, LoginActivity::class.java)
                 context?.startActivity(intent)
             }
             else if ((menuList[position].itemQuantity).toInt()<=0) {
                 Toast.makeText(context, "Out of Stock!!", Toast.LENGTH_LONG).show()
             }
             else{
                 callbackSubCategoryDescription.addToCartData(menuList[position].itemName, menuList[position].itemDescription,menuList[position].itemPrice,
                     menuList[position].itemMainCategoryName,menuList[position].itemSubCategoryName, menuList[position].itemFoodType,menuList[position].itemQuantity,
                     menuList[position].itemId,menuList[position].itemImageUrl)
             }
         }

        holder.binding.addOnsButton.setOnClickListener {
          /*  callbackSubCategoryDescription.addOnsButtonClickEvent(menuList[position].itemName, menuList[position].itemDescription,menuList[position].itemPrice,
                menuList[position].itemMainCategoryName,menuList[position].itemSubCategoryName, menuList[position].itemFoodType,menuList[position].itemBaseQuantity,
                menuList[position].itemId,menuList[position].itemImageUrl)*/
            val sharedPreferences = context?.getSharedPreferences(context?.resources?.getString(R.string.registration_details_sharedPreferences), Context.MODE_PRIVATE)
            userId = sharedPreferences?.getString("userId", "")
            if (userId.equals("") || userId.equals(null)) {
                Toast.makeText(context, "Before Add to Cart Please Register!!", Toast.LENGTH_LONG).show()
                val intent = Intent(context, LoginActivity::class.java)
                context?.startActivity(intent)
            }else if ((menuList[position].itemQuantity).toInt()<=0) {
                Toast.makeText(context, "Out of Stock!!", Toast.LENGTH_LONG).show()
            }else{
                callbackSubCategoryDescription.addToCartData(menuList[position].itemName, menuList[position].itemDescription,menuList[position].itemPrice,
                    menuList[position].itemMainCategoryName,menuList[position].itemSubCategoryName, menuList[position].itemFoodType,menuList[position].itemQuantity,
                    menuList[position].itemId,menuList[position].itemImageUrl)
            }
        }


    }


    override fun getItemCount(): Int {
        return menuList.size
    }

    class MyViewHolder(val binding: ItemRowSubcategoryDescriptionBinding):RecyclerView.ViewHolder(binding.root){
        val tvTitle = binding.tvItemName
        val tvDescription =binding.tvDescription
        val tvPrice = binding.tvPrice
        val vegNonVegImageview = binding.vegOrNonVegImageView

        fun bind(data:SubCategoryMenuData){
         tvTitle.text = data.itemName
         tvDescription.text = data.itemDescription
         tvPrice.text = "AED"+" "+data.itemPrice
           if (data.itemFoodType) {
              vegNonVegImageview.setBackgroundResource(R.drawable.veg)
           }else{
               vegNonVegImageview.setBackgroundResource(R.drawable.non_veg)
           }

       }

    }
}