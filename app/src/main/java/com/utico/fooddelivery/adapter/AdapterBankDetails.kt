package com.utico.fooddelivery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utico.fooddelivery.databinding.ItemRowBankDetailsBinding

class AdapterBankDetails : RecyclerView.Adapter<AdapterBankDetails.myViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRowBankDetailsBinding.inflate(inflater,parent,false)
        return myViewHolder(binding)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return 2
    }

    class myViewHolder(val binding: ItemRowBankDetailsBinding) : RecyclerView.ViewHolder(binding.root)
}