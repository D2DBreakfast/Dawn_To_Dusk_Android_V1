package com.utico.fooddelivery.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utico.fooddelivery.databinding.ItemRowPackageOrderHistoryBinding
import com.utico.fooddelivery.view.AddFragmentToActivity

class AdapterPackageOrderHistory : RecyclerView.Adapter<AdapterPackageOrderHistory.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ItemRowPackageOrderHistoryBinding.inflate(inflate,parent,false)


        /*Click event for the view the Order history*/
        val cardview = binding.orderHistoryCardview
            cardview.setOnClickListener {
                val intent = Intent(parent.context, AddFragmentToActivity::class.java)
                intent.putExtra("FragmentName","MealOrderPackageDetailsFragment")
                parent.context.startActivity(intent)
            }

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return 20
    }

    class MyViewHolder(val binding: ItemRowPackageOrderHistoryBinding) :RecyclerView.ViewHolder(binding.root)

}