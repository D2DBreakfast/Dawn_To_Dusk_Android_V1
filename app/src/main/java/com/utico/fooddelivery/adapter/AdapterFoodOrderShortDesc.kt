package com.utico.fooddelivery.adapter

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utico.fooddelivery.R
import com.utico.fooddelivery.databinding.ItemRowFoodOrderDescBinding
import com.utico.fooddelivery.model.DataX
import com.utico.fooddelivery.view.AddFragmentToActivity

class AdapterFoodOrderShortDesc : RecyclerView.Adapter<AdapterFoodOrderShortDesc.MyViewHolder>() {
    var foodDescList = mutableListOf<DataX>()
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
       holder.bind(foodDescList[position])

         holder.cardView.setOnClickListener {
             foodName= holder.tvName.text.toString()
             description= holder.tvDescription.text.toString()
             price= holder.tvPrice.text.toString()
             val sharedPreferences = context?.getSharedPreferences(context?.resources?.getString(R.string.order_details_sharedPreferences),MODE_PRIVATE)
             val editor = sharedPreferences?.edit()
             // Toast.makeText(context,foodName + description+price,Toast.LENGTH_LONG).show()
             val intent = Intent (context, AddFragmentToActivity::class.java)
             intent.putExtra("FragmentName","OrderDetailsFragment")
             editor?.putString("foodName",foodName)
             editor?.putString("foodDescription",description)
             editor?.putString("foodPrice",price)
             editor?.commit()
             context?.startActivity(intent)
         }





    }

    override fun getItemCount(): Int {
       return foodDescList.size
    }

    class MyViewHolder(val binding: ItemRowFoodOrderDescBinding): RecyclerView.ViewHolder(binding.root){
        private var vegnonveg: String? = null
        val cardView = binding.cardview
        val tvName = binding.tvTitle
        val tvDescription = binding.tvDescription
        val tvPrice = binding.tvPrice
        val imageview = binding.imageView
        val imageViewVegOrNonveg = binding.imageVegOrNonveg

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
        }

    }
}