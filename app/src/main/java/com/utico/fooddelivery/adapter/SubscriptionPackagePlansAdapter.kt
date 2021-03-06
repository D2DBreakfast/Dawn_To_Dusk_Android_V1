package com.utico.fooddelivery.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utico.fooddelivery.`interface`.CallbackSubscription
import com.utico.fooddelivery.databinding.ItemRowSubscriptionPackagePlansBinding
import com.utico.fooddelivery.model.SubscriptionPlan

class SubscriptionPackagePlansAdapter(val callbackSubscription: CallbackSubscription):RecyclerView.Adapter<SubscriptionPackagePlansAdapter.MyViewHolder>() {
    var packageTypeList = mutableListOf<SubscriptionPlan>()
    var context:Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRowSubscriptionPackagePlansBinding.inflate(inflater,parent,false)
        return MyViewHolder(binding)}

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(packageTypeList[position])
        val radioButton = holder.binding.radioButton
            radioButton.setOnClickListener {
                if (radioButton.isChecked) {
                    for (item in packageTypeList) {
                        item.isSelected = false
                    }
                    packageTypeList[position].isSelected = true
                        callbackSubscription.subscriptionPlan(packageTypeList[position].days, packageTypeList[position].price, packageTypeList[position].subscriptionTitle, packageTypeList[position].subscriptionId)
                        notifyDataSetChanged()
                }
        }
    }

    override fun getItemCount(): Int {
      return packageTypeList.size
    }

   class MyViewHolder(val binding: ItemRowSubscriptionPackagePlansBinding):RecyclerView.ViewHolder(binding.root){
       val tvPlanType = binding.tvPlanType
       val radioButton = binding.radioButton
       fun bind(data:SubscriptionPlan){
           tvPlanType.text = data.days + " "+ "day"+" "+"AED"+data.price
           radioButton.isChecked = data.isSelected
       }
   }

}