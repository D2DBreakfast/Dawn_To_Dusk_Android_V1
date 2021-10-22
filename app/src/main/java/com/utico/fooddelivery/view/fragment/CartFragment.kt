package com.utico.fooddelivery.view.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
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
import com.utico.fooddelivery.`interface`.CallbackAddToCartDetails
import com.utico.fooddelivery.adapter.AdapterCart
import com.utico.fooddelivery.databinding.FragmentCartBinding
import com.utico.fooddelivery.model.AddToCartDetailsResponseModel
import com.utico.fooddelivery.model.CartData
import com.utico.fooddelivery.model.OrderPlacedResponse
import com.utico.fooddelivery.view.AddFragmentToActivity
import com.utico.fooddelivery.view.DashBoardActivity
import com.utico.fooddelivery.view.RegistrationActivity
import com.utico.fooddelivery.viewmodel.CartViewModel

class CartFragment : Fragment(),CallbackAddToCartDetails {
    private lateinit var viewModel: CartViewModel
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    lateinit var adapterCart: AdapterCart
    private var sharedPreferences:SharedPreferences? = null
    private var cartDetailsSharedPreferences:SharedPreferences? = null
    private var mainCategoryName:String? = null
    private var subCategoryName:String? = null
    private var foodType:String = "Veg"
    private var foodName:String? = null
    private var foodId:String? = null
    private var foodQuantity:String? = null
    private var description:String? = null
    private var userId:String? = null
    private var foodPrice:String? = null

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
        itemQuantity = cartDetailsSharedPreferences?.getString("itemQuantity","")
        description = cartDetailsSharedPreferences?.getString("itemPrice","")
        itemUserId = cartDetailsSharedPreferences?.getString("userId","")*/

        val root: View = binding.root
        toCheckUserRegisterOrNot(userId)
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
       adapterCart = AdapterCart(this)
       cartRecyclerView.adapter = adapterCart
    }



    /*Theis function is used to set all the btnAddToCart click event here*/
    fun setButtonClickEvent(){
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

    }

    fun setUpCartData(){
       viewModel.getCartDetailsObservable().observe(viewLifecycleOwner, Observer<AddToCartDetailsResponseModel> {
           if (it.cartData == null){
               Toast.makeText(context,it.message,Toast.LENGTH_LONG).show()
           }else {
               adapterCart.addToCartList = it.cartData.toMutableList()
               adapterCart.notifyDataSetChanged()
           }
       })
        viewModel.apiCall(userId!!)
    }

    fun checkOutButton(){
        binding.btnCheckOut.setOnClickListener {
            viewModel.placeObservable().observe(viewLifecycleOwner, Observer<OrderPlacedResponse> {
                 Toast.makeText(context,it.message,Toast.LENGTH_SHORT).show()
                 if (it.message.equals("Successfully Added the Order Details")){
                     val intent = Intent(context,DashBoardActivity::class.java)
                     startActivity(intent)
                 }

            })
            viewModel.apiCallPlaceOrder(mainCategoryName!!,subCategoryName!!,foodType!!,foodName!!,foodId!!,foodQuantity!!,foodPrice!!,userId!!)

        }
    }


    override fun passAddToCartDetails(itemMainCategoryName: String, itemSubCategoryName: String, itemName: String, itemId: String, itemQuantity: String, itemPrice: String) {
                   mainCategoryName=itemMainCategoryName
                   subCategoryName = itemSubCategoryName
                   foodName =itemName
                   foodId = itemId
                   foodQuantity=itemQuantity
                   foodPrice = itemPrice

      /*  binding.btnCheckOut.setOnClickListener {
            viewModel.placeObservable().observe(viewLifecycleOwner, Observer<OrderPlacedResponse> {
                Toast.makeText(context,it.message,Toast.LENGTH_SHORT).show()
            })
            viewModel.apiCallPlaceOrder(itemMainCategoryName!!,itemSubCategoryName!!,"Veg",itemName!!,itemId!!,itemQuantity!!,"ggdg"!!,"4"!!)
        }*/

    }

    override fun passAddToCartList(cartData: MutableList<CartData>) {
       // viewModel.apiCallPlaceOrder(cartData!!)

    }


    fun alertDialog(message: String) {
        val dialogBuilder = AlertDialog.Builder(activity)
            dialogBuilder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Ok", DialogInterface.OnClickListener {

                        dialog, id ->activity?.finish()
                })


    }
 fun toCheckUserRegisterOrNot(userID: String?) {
      if (userID.equals("")||userID.equals(null)) {
          Toast.makeText(context,"Before Add to Cart Please Register!!",Toast.LENGTH_LONG).show()
          val intent = Intent(context,RegistrationActivity::class.java)
              context?.startActivity(intent)
      }
 }

}