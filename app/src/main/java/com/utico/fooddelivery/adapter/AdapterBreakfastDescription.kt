package com.utico.fooddelivery.adapter

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utico.fooddelivery.R
import com.utico.fooddelivery.databinding.ItemRowFoodOrderDescBinding
import com.utico.fooddelivery.model.DataX
import com.utico.fooddelivery.view.AddFragmentToActivity

class AdapterBreakfastDescription() : RecyclerView.Adapter<AdapterBreakfastDescription.MyViewHolder>() {
    var breakfastDescList = mutableListOf<DataX>()
    var foodName:String? = null
    var description:String? = null
    var price:String? = null
    var context:Context? = null




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MyViewHolder {
        context = parent.context
        var inflater = LayoutInflater.from(parent.context)
        var binding = ItemRowFoodOrderDescBinding.inflate(inflater,parent,false)
        return MyViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.bind(breakfastDescList[position])

         holder.cardView.setOnClickListener {
            /* foodName= holder.tvName.text.toString()
             description= holder.tvDescription.text.toString()
             price= holder.tvPrice.text.toString()*/
             val sharedPreferences = context?.getSharedPreferences(context?.resources?.getString(R.string.order_details_sharedPreferences),MODE_PRIVATE)
             val editor = sharedPreferences?.edit()
             // Toast.makeText(context,foodName + description+price,Toast.LENGTH_LONG).show()
             val intent = Intent (context, AddFragmentToActivity::class.java)
             intent.putExtra("FragmentName","ItemDescriptionFragment")
             editor?.putString("itemName",holder.tvName.text.toString())
             editor?.putString("itemDescription",holder.tvDescription.text.toString())
             editor?.putString("itemPrice",holder.tvPrice.text.toString())
             editor?.putString("itemMainCategoryName",holder.tvMainCategory.text.toString())
             editor?.putString("itemSubCategoryName",holder.tvSubCategoryName.text.toString())
             editor?.putString("itemFoodType",holder.tvFoodType.text.toString())
             editor?.putString("itemQuantity",holder.tvQuantity.text.toString())
             editor?.putString("itemId",holder.tvItemId.text.toString())
             editor?.commit()
             context?.startActivity(intent)
         }





    }

    override fun getItemCount(): Int {
       return breakfastDescList.size
    }

    class MyViewHolder(val binding: ItemRowFoodOrderDescBinding): RecyclerView.ViewHolder(binding.root){
        private var vegnonveg: String? = null
        val cardView = binding.cardview
        val tvName = binding.tvTitle
        val tvDescription = binding.tvDescription
        val tvPrice = binding.tvPrice
        val imageview = binding.imageView
        val imageViewVegOrNonveg = binding.imageVegOrNonveg

        val tvMainCategory = binding.tvMainCategoryName
        val tvSubCategoryName = binding.tvSubCategoryName
        val tvFoodType = binding.tvFoodType
        val tvQuantity = binding.tvQuantity
        val tvItemId = binding.tvItemId

        fun bind(data:DataX){
            vegnonveg = data.itemFoodType
            if (vegnonveg.equals("Veg")){
                imageViewVegOrNonveg.setBackgroundResource(R.drawable.veg)
            }else{
                imageViewVegOrNonveg.setBackgroundResource(R.drawable.non_veg)
            }
           tvName.text = data.itemName
           tvDescription.text = data.itemDescription
           tvPrice.text = "AED"+" "+data.itemPrice.toString()+".00"
          /* val imageUrl = data.avatar
            Picasso.get()
                .load(imageUrl)
                .into(imageview)*/

            tvMainCategory.text = data.itemMainCategoryName
            tvSubCategoryName.text = data.itemSubCategoryName
            tvFoodType.text = data.itemFoodType
            tvQuantity.text = data.itemQuantity
            tvItemId.text = data.itemId
        }

    }
}