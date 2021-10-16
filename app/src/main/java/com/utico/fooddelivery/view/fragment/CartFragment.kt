package com.utico.fooddelivery.view.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.utico.fooddelivery.R
import com.utico.fooddelivery.adapter.AdapterBrunchDescription
import com.utico.fooddelivery.adapter.AdapterCart
import com.utico.fooddelivery.databinding.FragmentCartBinding
import com.utico.fooddelivery.model.AddToCartDetailsResponseModel
import com.utico.fooddelivery.model.FoodList
import com.utico.fooddelivery.model.OrderPlacedResponse
import com.utico.fooddelivery.view.AddFragmentToActivity
import com.utico.fooddelivery.viewmodel.CartViewModel

class CartFragment : Fragment() {
    private lateinit var viewModel: CartViewModel
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    lateinit var adapterCart: AdapterCart
    private var sharedPreferences:SharedPreferences? = null
    private var cartDetailsSharedPreferences:SharedPreferences? = null
    private var userId:String? = null
    private var itemMainCategoryName:String? = null
    private var itemSubCategoryName:String? = null
    private var itemFoodType:String? = null
    private var itemName:String? = null
    private var itemId:String? = null
    private var itemQuantity:String? = null
    private var description:String? = null
    private var itemUserId:String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        sharedPreferences = context?.getSharedPreferences(resources.getString(R.string.registration_details_sharedPreferences),Context.MODE_PRIVATE)
        userId = sharedPreferences?.getString("userId","")
        cartDetailsSharedPreferences = context?.getSharedPreferences(context?.resources?.getString(R.string.cart_details_sharedPreferences),Context.MODE_PRIVATE)

        itemMainCategoryName = cartDetailsSharedPreferences?.getString("itemMainCategoryName","")
        itemSubCategoryName = cartDetailsSharedPreferences?.getString("itemSubCategoryName","")
        itemFoodType = cartDetailsSharedPreferences?.getString("itemFoodType","")
        itemName = cartDetailsSharedPreferences?.getString("itemName","")
        itemId = cartDetailsSharedPreferences?.getString("itemId","")
        itemQuantity = cartDetailsSharedPreferences?.getString("itemQuantity","")
        description = cartDetailsSharedPreferences?.getString("itemPrice","")
        itemUserId = cartDetailsSharedPreferences?.getString("userId","")
        val root: View = binding.root
        setButtonClickEvent()
        initAdapter()
        setUpCartData()
        checkOutButton()
        return root

    }

    override fun onDestroyView() { super.onDestroyView()
        _binding = null
    }

   fun initAdapter(){
      val cartRecyclerView = binding.cartRecyclerview
       cartRecyclerView.layoutManager = LinearLayoutManager(activity)
       adapterCart = AdapterCart()
       cartRecyclerView.adapter = adapterCart
    }



    /*Theis function is used to set all the button click event here*/
    fun setButtonClickEvent(){
        binding.tvCoupons.setOnClickListener {
          val intent = Intent(context,AddFragmentToActivity::class.java)
              intent.putExtra("FragmentName","CouponsFragment")
              startActivity(intent)
        }

        /*button click on order tracking */
        binding.fltBtnOrderTracking.setOnClickListener {
            val intent = Intent(context,AddFragmentToActivity::class.java)
                 intent.putExtra("FragmentName","OrderTrackingFragment")
                startActivity(intent)
        }

    }

    fun setUpCartData(){
       viewModel.getCartDetailsObservable().observe(viewLifecycleOwner, Observer<AddToCartDetailsResponseModel> {
         adapterCart.addToCartDetailsList = it.cartData.toMutableList()
         adapterCart.notifyDataSetChanged()
       })
        viewModel.apiCall(userId!!)
    }

    fun checkOutButton(){
        binding.btnCheckOut.setOnClickListener {
            viewModel.placeObservable().observe(viewLifecycleOwner, Observer<OrderPlacedResponse> {
                 Toast.makeText(context,it.message,Toast.LENGTH_SHORT).show()
            })
            viewModel.apiCallPlaceOrder(itemMainCategoryName!!,itemSubCategoryName!!,itemFoodType!!,itemName!!,itemId!!,itemQuantity!!,description!!,itemUserId!!)
        }
    }



}