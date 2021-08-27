package com.utico.fooddelivery.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.utico.fooddelivery.R
import com.utico.fooddelivery.databinding.ItemRowFoodOrderDescBinding
import com.utico.fooddelivery.model.UserInfo
import com.utico.fooddelivery.view.fragment.OrderDetailsFragment

class AdapterFoodOrderShortDesc : RecyclerView.Adapter<AdapterFoodOrderShortDesc.MyViewHolder>() {
    var foodDescList = mutableListOf<UserInfo>()

    fun foodOrderDescList(foodorderdesc:List<UserInfo>){
        this.foodDescList = foodorderdesc.toMutableList()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterFoodOrderShortDesc.MyViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var binding = ItemRowFoodOrderDescBinding.inflate(inflater,parent,false)

         val cardview = binding.cardview
           cardview.setOnClickListener {
            Toast.makeText(parent.context,"Click On Order Details",Toast.LENGTH_LONG).show()
              //var view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_food_details, parent, false)
               val activity = parent.context as AppCompatActivity
               val orderDetailsFragment = OrderDetailsFragment()
               activity.supportFragmentManager.beginTransaction().replace(R.id.dashBoardLayout,orderDetailsFragment).addToBackStack(null).commit()


           }

        return MyViewHolder(binding)

    }

    override fun onBindViewHolder(holder: AdapterFoodOrderShortDesc.MyViewHolder, position: Int) {
      //  val foodorderdesc = foodDescList[position]

    }

    override fun getItemCount(): Int {
       return 20
    }

    class MyViewHolder(val binding: ItemRowFoodOrderDescBinding): RecyclerView.ViewHolder(binding.root){

    }
}