package com.utico.fooddelivery.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.utico.fooddelivery.`interface`.CallbackPlaceOrder
import com.utico.fooddelivery.databinding.ItemRowCartBinding
import com.utico.fooddelivery.model.CartData
import com.utico.fooddelivery.model.PlaceOrderArray

class AdapterCart(val callbackPlaceOrder: CallbackPlaceOrder): RecyclerView.Adapter<AdapterCart.MyViewHolder>() {
    var addToCartList = mutableListOf<CartData>()
    var toGetPlaceOrderList = mutableListOf<CartData>()
    private var context: Context? = null
    private var isCheck:Boolean? = true


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       context = parent.context
       val inflater = LayoutInflater.from(parent.context)
       val binding = ItemRowCartBinding.inflate(inflater,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.bind(addToCartList[position], callbackPlaceOrder)
            callbackPlaceOrder.passPlaceOrderList(addToCartList,position)


        holder.binding.tvIncrement.setOnClickListener {
            val itemQuantity = addToCartList[position].itemQuantity.toInt()
            addToCartList[position].itemQuantity = (itemQuantity + 1).toString()
            val basePrice = addToCartList[position].itemPrice.toInt()
            addToCartList[position].itemPrice = (addToCartList[position].itemQuantity.toInt() * basePrice).toString()
            notifyItemChanged(position)
           // callbackPlaceOrder.passPlaceOrderList("#Vijaynagar Benguluru","Vij560040","300","2",addToCartList)
           // Toast.makeText(context,"This option under developing..",Toast.LENGTH_LONG).show()
        }

        holder.binding.tvDecrement.setOnClickListener {
             if ( addToCartList[position].itemQuantity.toInt() >0) {
                 val itemQuantity = addToCartList[position].itemQuantity.toInt()
                     addToCartList[position].itemQuantity = (itemQuantity - 1).toString()
                 val basePrice = addToCartList[position].itemPrice.toInt()
                     addToCartList[position].itemPrice =
                     (addToCartList[position].itemQuantity.toInt() * basePrice).toString()
                     notifyItemChanged(position)
                     //callbackPlaceOrder.passPlaceOrderList(addToCartList)
                     //Toast.makeText(context, "This option under developing..", Toast.LENGTH_LONG).show()
             }else{
                 Toast.makeText(context, "Quantity Should not be Zero..", Toast.LENGTH_LONG).show()

             }

        }
    }


    override fun getItemCount(): Int {
        return addToCartList.size

    }

    class MyViewHolder(val binding: ItemRowCartBinding): RecyclerView.ViewHolder(binding.root){
        val tvTitle = binding.tvTitle
        val tvPrice = binding.tvPrice
        val tvQuantity =binding.tvQuantity
        var placeOrderListData = mutableListOf<PlaceOrderArray>()
        fun bind(data: CartData, callbackPlaceOrder: CallbackPlaceOrder)
           {
            tvTitle.text = data.itemName
            tvPrice.text = "AED"+" "+ data.itemPrice
            tvQuantity.text = data.itemQuantity

              /* placeOrderDataSet = PlaceOrderArray("CART-3","Veg",data.itemId,data.itemMainCategoryName,
                   data.itemName,data.itemPrice,data.itemQuantity,data.itemSubCategoryName,data.orderStatus)
               placeOrderDataSet.cartId ="CART-3"
              placeOrderDataSet.itemFoodType ="Veg"
              placeOrderDataSet.itemId =data.itemId
              placeOrderDataSet.itemMainCategoryName=data.itemMainCategoryName
              placeOrderDataSet.itemName = data.itemName
              placeOrderDataSet.itemPrice=data.itemPrice
              placeOrderDataSet.itemQuantity =data.itemQuantity
              placeOrderDataSet.itemSubCategoryName= data.itemSubCategoryName
              placeOrderDataSet.orderStatus = data.orderStatus
               placeOrderListData.addAll(listOf(placeOrderDataSet))*/

               callbackPlaceOrder.passAddToCartDetails(data.itemMainCategoryName,data.itemSubCategoryName,
                     data.itemName,data.itemId,data.itemQuantity,data.itemPrice)


            //This Callback is used to send the list items
           /* val image_url = data.
            Picasso.get()
                .load(image_url)
                .into(imageView)*/
        }

    }
}

