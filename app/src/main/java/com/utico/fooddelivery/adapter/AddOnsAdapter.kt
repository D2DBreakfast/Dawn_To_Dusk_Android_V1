package com.utico.fooddelivery.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utico.fooddelivery.`interface`.CallbackSubCategoryDescription
import com.utico.fooddelivery.databinding.ItemRowAddonsBinding
import com.utico.fooddelivery.model.AddOnDetails


class AddOnsAdapter(val callbackSubCategoryDescription: CallbackSubCategoryDescription) :
    RecyclerView.Adapter<AddOnsAdapter.MyViewHolder>() {
    var addOnsList = mutableListOf<AddOnDetails>()
    var context:Context? = null
    private var total: Int = 0
    private var radioButtonCheck: Boolean = true
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRowAddonsBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(addOnsList[position])
        val checkBox = holder.checkBox
        checkBox.setOnClickListener {
            if(checkBox.isChecked){
                total += (addOnsList[position].amount).toInt()
            } else {
                total -= (addOnsList[position].amount).toInt()
            }
            callbackSubCategoryDescription.getSelectedAddOnsValue(total.toString())
        }
    }

    override fun getItemCount(): Int {
        return addOnsList.size
    }

    class MyViewHolder(val binding: ItemRowAddonsBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvAddOnsName = binding.tvAddOnsName
        val tvAddOnsPrice = binding.tvAddOnsPrice
        val tvItemName   = binding.tvItemName
        val checkBox = binding.checkBox

        fun bind(data: AddOnDetails) {
            tvItemName.text = data.addOnName
            tvAddOnsName.text = data.addOnType
            tvAddOnsPrice.text = data.amount
        }
    }
}

