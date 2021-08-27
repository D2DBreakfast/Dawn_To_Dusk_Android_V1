package com.utico.fooddelivery.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.utico.fooddelivery.databinding.ItemRowMealPlanBinding
import com.utico.fooddelivery.model.MealPlan
import com.utico.fooddelivery.view.AddFragmentToActivity

class AdapterMealPlan:RecyclerView.Adapter<AdapterMealPlan.MyviewHolder>() {
      var mealPlanList = mutableListOf<MealPlan>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
      val inflater = LayoutInflater.from(parent.context)
      val binding = ItemRowMealPlanBinding.inflate(inflater,parent,false)
        val cardview = binding.cardview
        cardview.setOnClickListener {
            //var view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_food_details, parent, false)
            /*val activity = parent.context as AppCompatActivity
            val mealsPlanDetailsFragment = MealsPlanDetailsFragment()
            activity.supportFragmentManager.beginTransaction().replace(R.id.dashBoardLayout,mealsPlanDetailsFragment).addToBackStack(null).commit()
            */
            val intent = Intent (parent.context, AddFragmentToActivity::class.java)
            intent.putExtra("FragmentName","MealsPlanDetailsFragment")
            parent.context.startActivity(intent)

        }
      return MyviewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        holder.bind(mealPlanList[position])
    }

    override fun getItemCount(): Int {
        return mealPlanList.size

    }

    class MyviewHolder(val binding: ItemRowMealPlanBinding) :RecyclerView.ViewHolder(binding.root){
        val tvPackage = binding.tvPackage
        val tvDesc = binding.tvDescription
        val tvPrice = binding.tvPrice
        val imageView = binding.imageView

        fun bind(data:MealPlan){
          tvPackage.text = data.id.toString()
          tvDesc.text = data.email
          tvPrice.text = data.first_name

          val image_url = data.avatar
            Picasso.get()
                .load(image_url)
                .into(imageView)
        }
    }
}