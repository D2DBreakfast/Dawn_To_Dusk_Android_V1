package com.utico.fooddelivery.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.utico.fooddelivery.`interface`.CallbackViewCart
import com.utico.fooddelivery.databinding.ItemRowCartBinding
import com.utico.fooddelivery.model.itemArray
import com.utico.fooddelivery.model.ViewCartData

class AdapterCart(val callbackViewCart: CallbackViewCart): RecyclerView.Adapter<AdapterCart.MyViewHolder>() {
    var addToCartList = mutableListOf<ViewCartData>()
    var toGetPlaceOrderList = mutableListOf<ViewCartData>()
    private var context: Context? = null
    private var isCheck:Boolean? = true


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       context = parent.context
       val inflater = LayoutInflater.from(parent.context)
       val binding = ItemRowCartBinding.inflate(inflater,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.bind(addToCartList[position], callbackViewCart)
            callbackViewCart.passPlaceOrderList(addToCartList)

            holder.binding.tvDeleteItem.setOnClickListener {
             callbackViewCart.deleteItem(addToCartList[position].userId,addToCartList[position].cartId)
            }

        /*Quantity Decrement*/
        holder.binding.tvDecrement.setOnClickListener {
                val itemBaseQuantity = addToCartList[position].itemBaseQuantity.toInt()
               // val itemDecrement = (itemBaseQuantity - 1).toString()
                    addToCartList[position].itemBaseQuantity = (itemBaseQuantity - 1).toString()
                    callbackViewCart.decrementOrIncremenItem(addToCartList[position].userId,addToCartList[position].cartId,addToCartList[position].itemBaseQuantity,addToCartList[position].itemPrice)
                   notifyItemChanged(position)

            //callbackViewCart.passPlaceOrderList(addToCartList)
                //Toast.makeText(context, "This option under developing..", Toast.LENGTH_LONG).show()

        }
        /*Quantity Decrement*/
        holder.binding.tvIncrement.setOnClickListener {
                val itemBaseQuantity = addToCartList[position].itemBaseQuantity.toInt()
                    addToCartList[position].itemBaseQuantity = (itemBaseQuantity + 1).toString()
                   callbackViewCart.decrementOrIncremenItem(addToCartList[position].userId,addToCartList[position].cartId,addToCartList[position].itemBaseQuantity,addToCartList[position].itemPrice)
                   notifyItemChanged(position)

        }


        /*holder.binding.tvIncrement.setOnClickListener {
            val itemBaseQuantity = addToCartList[position].itemBaseQuantity.toInt()
            addToCartList[position].itemBaseQuantity = (itemBaseQuantity + 1).toString()
            val basePrice = addToCartList[position].itemPrice.toInt()
            addToCartList[position].itemPrice = (addToCartList[position].itemBaseQuantity.toInt() * basePrice).toString()
            notifyItemChanged(position)
           // callbackViewCart.passPlaceOrderList("#Vijaynagar Benguluru","Vij560040","300","2",addToCartList)
           // Toast.makeText(context,"This option under developing..",Toast.LENGTH_LONG).show()
        }*/

        /*holder.binding.tvDecrement.setOnClickListener {
             if ( addToCartList[position].itemBaseQuantity.toInt() >0) {
                 val itemBaseQuantity = addToCartList[position].itemBaseQuantity.toInt()
                     addToCartList[position].itemBaseQuantity = (itemBaseQuantity - 1).toString()
                 val basePrice = addToCartList[position].itemPrice.toInt()
                     addToCartList[position].itemPrice =
                     (addToCartList[position].itemBaseQuantity.toInt() * basePrice).toString()
                     notifyItemChanged(position)
                     //callbackViewCart.passPlaceOrderList(addToCartList)
                     //Toast.makeText(context, "This option under developing..", Toast.LENGTH_LONG).show()
             }else{
                 Toast.makeText(context, "Quantity Should not be Zero..", Toast.LENGTH_LONG).show()

             }

        }*/


    }


    override fun getItemCount(): Int {
        return addToCartList.size

    }

    class MyViewHolder(val binding: ItemRowCartBinding): RecyclerView.ViewHolder(binding.root){
        val tvTitle = binding.tvItemName
        val tvPrice = binding.tvPrice
        val tvItemBaseQuantity =binding.tvItemBaseQuantity
        var placeOrderListData = mutableListOf<itemArray>()
        fun bind(data: ViewCartData, callbackViewCart: CallbackViewCart)
           {
            tvTitle.text = data.itemName
            tvPrice.text = "AED"+" "+ data.itemPrice
               tvItemBaseQuantity.text = data.itemBaseQuantity

              /* placeOrderDataSet = itemArray("CART-3","Veg",data.itemId,data.itemMainCategoryName,
                   data.itemName,data.itemPrice,data.itemBaseQuantity,data.itemSubCategoryName,data.orderStatus)
               placeOrderDataSet.cartId ="CART-3"
              placeOrderDataSet.itemFoodType ="Veg"
              placeOrderDataSet.itemId =data.itemId
              placeOrderDataSet.itemMainCategoryName=data.itemMainCategoryName
              placeOrderDataSet.itemName = data.itemName
              placeOrderDataSet.itemPrice=data.itemPrice
              placeOrderDataSet.itemBaseQuantity =data.itemBaseQuantity
              placeOrderDataSet.itemSubCategoryName= data.itemSubCategoryName
              placeOrderDataSet.orderStatus = data.orderStatus
               placeOrderListData.addAll(listOf(placeOrderDataSet))*/

              /* callbackViewCart.passAddToCartDetails(data.itemMainCategoryName,data.itemSubCategoryName,
                     data.itemName,data.itemId,data.itemBaseQuantity,data.itemPrice)*/


            //This Callback is used to send the list items
           /* val image_url = data.
            Picasso.get()
                .load(image_url)
                .into(imageView)*/
        }

    }
}

