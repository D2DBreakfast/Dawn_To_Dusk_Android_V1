package com.utico.fooddelivery.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.utico.fooddelivery.R
import com.utico.fooddelivery.`interface`.CallbackHome
import com.utico.fooddelivery.databinding.ItemRowMaincategoryBinding
import com.utico.fooddelivery.model.MainCategoryData

class MainCategoryListAdapter(val callbackHome: CallbackHome):RecyclerView.Adapter<MainCategoryListAdapter.MyViewHolder>() {
   var mainCategoryList = mutableListOf<MainCategoryData>()
    var contex:Context? = null
    var indexPosition:Int = 0
    private lateinit var binding:ItemRowMaincategoryBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        contex=parent.context
      val inflater = LayoutInflater.from(parent.context)
         binding  = ItemRowMaincategoryBinding.inflate(inflater,parent,false)
      return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(mainCategoryList[position])
        holder.relativeLayout.setOnClickListener {
            indexPosition = position
            notifyDataSetChanged()
        }
        if (indexPosition == position){
            //holder.viewLine.visibility = View.VISIBLE
            holder.tvCategoryName.paintFlags = Paint.UNDERLINE_TEXT_FLAG
            holder.tvCategoryName.setTextColor(contex!!.resources.getColor(R.color.green))
            callbackHome.mainCategoryClickEvent(mainCategoryList[position].mainCategoryId,position)
        }else{
            holder.viewLine.visibility = View.GONE
            holder.tvCategoryName.paintFlags = Paint.END_HYPHEN_EDIT_INSERT_UCAS_HYPHEN
            holder.tvCategoryName.setTextColor(contex!!.resources.getColor(R.color.grey_dark))
        }
    }

    override fun getItemCount(): Int {
        return mainCategoryList.size
    }

    class MyViewHolder(binding: ItemRowMaincategoryBinding):RecyclerView.ViewHolder(binding.root){
        val tvCategoryName = binding.tvCategoryName
        val relativeLayout = binding.relativeLayout
        val viewLine = binding.viewLine
        fun bind(data: MainCategoryData){
          tvCategoryName.text = data.mainCategoryName
          //relativeLayout.isClickable = data.isSelected
        }

    }
}