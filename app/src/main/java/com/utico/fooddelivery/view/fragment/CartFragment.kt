package com.utico.fooddelivery.view.fragment

import com.utico.fooddelivery.model.DeleteCartItemResponse
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.utico.fooddelivery.R
import com.utico.fooddelivery.`interface`.CallbackViewCart
import com.utico.fooddelivery.adapter.AdapterCart
import com.utico.fooddelivery.databinding.FragmentCartBinding
import com.utico.fooddelivery.model.*
import com.utico.fooddelivery.view.AddFragmentToActivity
import com.utico.fooddelivery.view.DashBoardActivity
import com.utico.fooddelivery.view.RegistrationActivity
import com.utico.fooddelivery.viewmodel.CartViewModel

class CartFragment : Fragment(),CallbackViewCart {
    private lateinit var viewModel: CartViewModel
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    lateinit var adapterCart: AdapterCart
    private var sharedPreferences:SharedPreferences? = null
    private var cartDetailsSharedPreferences:SharedPreferences? = null
    private lateinit var editor: SharedPreferences.Editor

    private var mainCategoryName:String? = null
    private var subCategoryName:String? = null
    private var foodType:String = "Veg"
    private var foodName:String? = null
    private var foodId:String? = null
    private var foodQuantity:String? = null
    private var description:String? = null
    private var userId:String? = null
    private var foodPrice:String? = null
    private var placeOrderSetData = mutableListOf<itemArray>()
    private var viewCartList = mutableListOf<itemArray>()
    private lateinit var itemArray: itemArray
    private lateinit var placeOrderSendDataModel: PlaceOrderSendDataModel
    private var villaNo:String? = null
    private var landMark:String? = null
    private var totalAmount:String? = null
    private var categoryType:String? =""


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        sharedPreferences = context?.getSharedPreferences(resources.getString(R.string.registration_details_sharedPreferences),Context.MODE_PRIVATE)
        userId = sharedPreferences?.getString("userId","")


       /* cartDetailsSharedPreferences = context?.getSharedPreferences(context?.resources?.getString(R.string.cart_details_sharedPreferences),Context.MODE_PRIVATE)
        itemMainCategoryName = cartDetailsSharedPreferences?.getString("itemMainCategoryName","")
        itemSubCategoryName = cartDetailsSharedPreferences?.getString("itemSubCategoryName","")
        itemFoodType = cartDetailsSharedPreferences?.getString("itemFoodType","")
        itemName = cartDetailsSharedPreferences?.getString("itemName","")
        itemId = cartDetailsSharedPreferences?.getString("itemId","")
        itemBaseQuantity = cartDetailsSharedPreferences?.getString("itemBaseQuantity","")
        description = cartDetailsSharedPreferences?.getString("itemPrice","")
        itemUserId = cartDetailsSharedPreferences?.getString("userId","")*/

        val root: View = binding.root
        toCheckUserRegisterOrNot(userId)
        initAdapter()
        getViewCartData()
        buttonClicksOrSetupDataToUi()
        return root

    }

    override fun onDestroyView() { super.onDestroyView()
        _binding = null
    }


    fun toCheckUserRegisterOrNot(userID: String?) {
        if (userID.equals("")||userID.equals(null)) {
            Toast.makeText(context,"Before Add to Cart Please Register!!",Toast.LENGTH_LONG).show()
            val intent = Intent(context,RegistrationActivity::class.java)
            context?.startActivity(intent)
        }
    }/*To check the user is register or not*/



    fun initAdapter(){
      val cartRecyclerView = binding.cartRecyclerview
       cartRecyclerView.layoutManager = LinearLayoutManager(activity)
       adapterCart = AdapterCart(this)
       cartRecyclerView.adapter = adapterCart
   }



    /*Theis function is used to set all the btnAddToCart click event here*/
    fun buttonClicksOrSetupDataToUi(){
        binding.tvCoupons.setOnClickListener {
          val intent = Intent(context,AddFragmentToActivity::class.java)
              intent.putExtra("FragmentName","CouponsFragment")
              startActivity(intent)
        }

        /*btnAddToCart click on order tracking */
        binding.fltBtnOrderTracking.setOnClickListener {
            val intent = Intent(context,AddFragmentToActivity::class.java)
                 intent.putExtra("FragmentName","OrderTrackingFragment")
                startActivity(intent)
        }

       /*Checkout button*/
        binding.btnCheckOut.setOnClickListener {
            viewModel.placeObservable().observe(viewLifecycleOwner, Observer<OrderPlacedResponse> {
                Toast.makeText(context,it.message,Toast.LENGTH_SHORT).show()
                val naviController = findNavController()
                 naviController.popBackStack()
            })

            villaNo = binding.edtVillaNo.text.toString()
            landMark = binding.edtLandMark.text.toString()
            placeOrderSendDataModel= PlaceOrderSendDataModel(villaNo,landMark,placeOrderSetData,"Vij560040",totalAmount,userId,categoryType!!)
            viewModel.apiCallPlaceOrder(placeOrderSendDataModel)
           // println(placeOrderSetData)
           // Toast.makeText(context,placeOrderSetData.toString(),Toast.LENGTH_LONG).show()
        }

    }

    fun getViewCartData(){
       viewModel.getCartDetailsObservable().observe(viewLifecycleOwner, Observer<ViewCartResponseModel> {
           if (it.ViewCartData== null){
               Toast.makeText(context,it.message,Toast.LENGTH_LONG).show()
           }else {
               adapterCart.addToCartList = it.ViewCartData.toMutableList()
               adapterCart.notifyDataSetChanged()
               totalAmount = it.priceAfterVat
               setBackendDataToUi(it.itemCount,it.priceBeforeVat,it.deliveryCharge,it.itemVat,totalAmount!!)
           }
       })
        viewModel.apiCall(userId!!)
    }


    private fun setBackendDataToUi(itemCount:String,itemCountPrice:String,deliveryCharge:String,vat:String,totalAmount:String){
        binding.tvItemCountLabel.text = resources.getString(R.string.item) +" "+ (itemCount)
        binding.tvItemCountPrice.text = resources.getString(R.string.aed)+ " "+ itemCountPrice
        binding.tvTotalPrice.text =  resources.getString(R.string.aed)+ " " + totalAmount
        binding.tvVatTotal.text =  resources.getString(R.string.aed)+ " " + vat
        binding.tvDeliveryCharge.text = resources.getString(R.string.aed)+ " " + deliveryCharge
    }/*Setup the Required ui data from the Backend*/


    override fun passPlaceOrderList(cartData: MutableList<ViewCartData>) {
        placeOrderSetData.clear()
        for (item in cartData){
            itemArray = itemArray(item.cartId,item.itemFoodType, item.itemId, item.itemMainCategoryName,
                item.itemName, item.itemPrice, item.itemBaseQuantity, item.itemSubCategoryName)
                placeOrderSetData.addAll(listOf(itemArray))
               // viewCartList = placeOrderSetData.distinct() as MutableList<itemArray>
        }

    }/*get the ViewCart data from interface*/

    override fun deleteItem(userId: String, cartId: String) {
        viewModel.deleteCardItemObservable().observe(viewLifecycleOwner, Observer<DeleteCartItemResponse> {
           if (it.statusCode == 400){
               Toast.makeText(context,it.message,Toast.LENGTH_LONG).show()
           }else{
               Toast.makeText(context,it.message,Toast.LENGTH_LONG).show()
               getViewCartData()
           }
        })
        viewModel.apiCallCartItemDelete(userId,cartId)
    }

    override fun decrementOrIncremenItem(userId: String, cartId: String, itemBaseQuantity: String, itemPrice: String) {
       // Toast.makeText(context,itemBaseQuantity+"  "+itemPrice,Toast.LENGTH_LONG).show()
        viewModel.incrementOrDecrementCartObservable().observe(viewLifecycleOwner, Observer<CartIncrementOrDecrementResponse> {
            getViewCartData()
        })
        viewModel.callApiIcrementDecrementCartItem(userId,cartId,itemBaseQuantity)

    }

}
