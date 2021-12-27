package com.utico.fooddelivery.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utico.fooddelivery.R
import com.utico.fooddelivery.`interface`.CallbackSubscription
import com.utico.fooddelivery.databinding.ItemRowSubscriptionPackagesBinding
import com.utico.fooddelivery.model.subscriptionTypes
import com.utico.fooddelivery.view.AddFragmentToActivity

class SubscriptionPackageAdapter:RecyclerView.Adapter<SubscriptionPackageAdapter.MyViewHolder>() {
    private var context:Context? = null
    private var callbackSubscription:CallbackSubscription? = null
    var subscriptionTypeList = mutableListOf<subscriptionTypes>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       context=parent.context
      val inflater = LayoutInflater.from(parent.context)
      val binding = ItemRowSubscriptionPackagesBinding.inflate(inflater,parent,false)
      return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
         holder.bind(subscriptionTypeList[position])
         holder.binding.relativeLayout.setOnClickListener {
             val sharedPreferences =context?.getSharedPreferences(context?.resources?.getString(R.string.subscription_plan_details_sharedPreferences),Context.MODE_PRIVATE)
             val editor = sharedPreferences?.edit()
             val intent = Intent(context,AddFragmentToActivity::class.java)
                intent.putExtra("FragmentName","SubscriptionDetailsFragment")
                editor?.putString("subscriptionDescription",subscriptionTypeList[position].subscriptionDescription)
                editor?.putString("subscriptionId",subscriptionTypeList[position].subscriptionId)
                editor?.putString("subscriptionImage",subscriptionTypeList[position].subscriptionImage)
                editor?.putString("subscriptionLeastAmount",subscriptionTypeList[position].subscriptionLeastAmount)
                editor?.putString("subscriptionType",subscriptionTypeList[position].subscriptionType)
                editor?.commit()
             context?.startActivity(intent)
             callbackSubscription?.subscriptionType(subscriptionTypeList[position].subscriptionDescription,subscriptionTypeList[position].subscriptionId,subscriptionTypeList[position].subscriptionImage,
             subscriptionTypeList[position].subscriptionLeastAmount,subscriptionTypeList[position].subscriptionType)
        }
    }

    override fun getItemCount(): Int {
        return subscriptionTypeList.size
    }

    class MyViewHolder(val binding: ItemRowSubscriptionPackagesBinding):RecyclerView.ViewHolder(binding.root){
        val tvSubscriptionType = binding.tvSubscriptionType
        val tvDescription = binding.tvDescription
        val tvPrice = binding.tvPrice
        fun bind(data: subscriptionTypes){
            tvSubscriptionType.text = data.subscriptionType
            tvDescription.text = data.subscriptionDescription
            tvPrice.text = data.subscriptionLeastAmount
      }
    }
}