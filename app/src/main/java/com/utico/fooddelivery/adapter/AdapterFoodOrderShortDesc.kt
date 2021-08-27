package com.utico.fooddelivery.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.utico.fooddelivery.`interface`.AddFragment
import com.utico.fooddelivery.databinding.ItemRowFoodOrderDescBinding
import com.utico.fooddelivery.model.FoodShortDesc
import com.utico.fooddelivery.view.AddFragmentToActivity

class AdapterFoodOrderShortDesc : RecyclerView.Adapter<AdapterFoodOrderShortDesc.MyViewHolder>() {
    var foodDescList = mutableListOf<FoodShortDesc>()
    var addFragment: AddFragment? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterFoodOrderShortDesc.MyViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var binding = ItemRowFoodOrderDescBinding.inflate(inflater,parent,false)

         val cardview = binding.cardview
           cardview.setOnClickListener {
            Toast.makeText(parent.context,"Click On Order Details",Toast.LENGTH_LONG).show()
              //var view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_food_details, parent, false)
               /*val activity = parent.context as AppCompatActivity
               val orderDetailsFragment = OrderDetailsFragment()
               activity.supportFragmentManager.beginTransaction().replace(R.id.dashBoardLayout,orderDetailsFragment).addToBackStack(null).commit()
*/
               val intent = Intent (parent.context, AddFragmentToActivity::class.java)
                   intent.putExtra("FragmentName","OrderDetailsFragment")
                   parent.context.startActivity(intent)

           }

        return MyViewHolder(binding)

    }

    override fun onBindViewHolder(holder: AdapterFoodOrderShortDesc.MyViewHolder, position: Int) {
       holder.bind(foodDescList[position])
    }

    override fun getItemCount(): Int {
       return foodDescList.size
    }

    class MyViewHolder(val binding: ItemRowFoodOrderDescBinding): RecyclerView.ViewHolder(binding.root){
        val tvName = binding.tvTitle
        val tvDescription = binding.tvDescription
        val tvPrice = binding.tvPrice
        val imageview = binding.imageView

        fun bind(data:FoodShortDesc){
           tvName.text = data.id.toString()
           tvDescription.text = data.email
           tvPrice.text = data.first_name
           val imageUrl = data.avatar
            Picasso.get()
                .load(imageUrl)
                .into(imageview)
        }

    }
}