package com.utico.fooddelivery.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.utico.fooddelivery.databinding.ItemRowNotificationBinding
import com.utico.fooddelivery.model.UserNotification
import com.utico.fooddelivery.view.AddFragmentToActivity

class AdapterNotification :RecyclerView.Adapter<AdapterNotification.myViewHolder>() {
    var notificationList = mutableListOf<UserNotification>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var binding = ItemRowNotificationBinding.inflate(inflater,parent,false)

        val cardview = binding.cardview
        cardview.setOnClickListener {
            val activity = parent.context as AppCompatActivity
           /* val notificationDetailsFragment = NotificationDetailsFragment()
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.dashBoardLayout,notificationDetailsFragment)
                .addToBackStack(null).commit()*/
            val intent = Intent (parent.context, AddFragmentToActivity::class.java)
            intent.putExtra("FragmentName","NotificationDetailsFragment")
            parent.context.startActivity(intent)

        }
        return myViewHolder(binding)

    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.bind(notificationList[position])
    }

    override fun getItemCount(): Int {
       return notificationList.size
    }

    class myViewHolder(val binding: ItemRowNotificationBinding) : RecyclerView.ViewHolder(binding.root){

        var tvTitle = binding.tvTitle
        var tvDesc = binding.tvDescription
        var imageview = binding.imageView

        fun bind(data : UserNotification){
          tvTitle.text ="Notification"+data.id.toString()
           tvDesc.text = data.email
            val image_url = data.avatar
            Picasso.get()
                .load(image_url)
                .into(imageview)
        }
    }

}