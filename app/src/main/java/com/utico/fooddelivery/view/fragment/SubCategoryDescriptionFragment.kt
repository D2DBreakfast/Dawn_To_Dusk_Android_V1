package com.utico.fooddelivery.view.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.utico.fooddelivery.R
import com.utico.fooddelivery.`interface`.CallbackSubCategoryDescription
import com.utico.fooddelivery.adapter.AdapterSubCategoryDescription
import com.utico.fooddelivery.adapter.AddOnsAdapter
import com.utico.fooddelivery.databinding.SubCategoryDescriptionFragmentBinding
import com.utico.fooddelivery.model.*
import com.utico.fooddelivery.viewmodel.SubCategoryDescriptionViewModel
import java.util.*
import kotlin.collections.ArrayList

class SubCategoryDescriptionFragment : Fragment(), CallbackSubCategoryDescription {
    private lateinit var binding: SubCategoryDescriptionFragmentBinding
    private lateinit var viewModel: SubCategoryDescriptionViewModel
    private lateinit var adapterSubCategoryDescription: AdapterSubCategoryDescription
    private lateinit var addOnsAdapter: AddOnsAdapter
    private lateinit var sharedPreferences: SharedPreferences
    private var mainCategoryId: String? = null
    private var subCategoryId: String? = null
    lateinit var adapter: ArrayAdapter<String>
    lateinit var listView: ListView
    private var subCategoryName: String? = null
    private var menuItemList = mutableListOf<SubCategoryMenuData>()
    private lateinit var tvAddToCart: TextView
    private var registrationSharedPreferences: SharedPreferences? = null
    private var userId: String? = null
    private var itemTotal: String? = null
    private var addOnPrice: String? = null
    private lateinit var tvItemTotalPrice: TextView
    private var itemBasePrice: String? = null
    private lateinit var dialogAddToCart:BottomSheetDialog
    private lateinit var dialogAddOns:BottomSheetDialog
    private var tcCheckAddons:Boolean? = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = SubCategoryDescriptionFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(SubCategoryDescriptionViewModel::class.java)
        sharedPreferences = (activity as AppCompatActivity).getSharedPreferences(
            resources.getString(R.string.subCategory_sharedPreference_data),
            Context.MODE_PRIVATE
        )
        registrationSharedPreferences = context?.getSharedPreferences(
            resources.getString(R.string.registration_details_sharedPreferences),
            Context.MODE_PRIVATE
        )
        userId = registrationSharedPreferences?.getString("userId", "")
        mainCategoryId = sharedPreferences.getString("mainCategoryId", "")
        subCategoryId = sharedPreferences.getString("subCategoryId", "")
        subCategoryName = sharedPreferences.getString("subCategoryName", "")
        binding.tvSubCategoryName.text = subCategoryName


        val view: View = binding.root
        initAdapter()
        getSuCategoryRelatedMenuData(mainCategoryId!!, subCategoryId!!)
        clickEvents()
        return view
    }

    private fun initAdapter() {
        val recyclerView = binding.CategoryDescriptionRecyclerview
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapterSubCategoryDescription = AdapterSubCategoryDescription(this)
        recyclerView.adapter = adapterSubCategoryDescription

    }

    private fun getSuCategoryRelatedMenuData(mainCategoryId: String, subCategoryId: String) {
        viewModel.subCategoryRelatedMenuDetailsObservable()
            .observe(viewLifecycleOwner, Observer<MenuDetailsResponseDataModel> {
                if (it.statusCode == 400) {
                    val tvErrorMessage = binding.tvErrorMessage
                    tvErrorMessage.visibility = View.VISIBLE
                    tvErrorMessage.text = it.message
                    Toast.makeText(context,it.message,Toast.LENGTH_LONG).show()
                } else {
                    adapterSubCategoryDescription.menuList = it.subCategoryMenuData.toMutableList()
                    menuItemList = it.subCategoryMenuData.toMutableList()
                    Toast.makeText(context,it.message,Toast.LENGTH_LONG).show()
                    adapterSubCategoryDescription.notifyDataSetChanged()
                }
            })
        viewModel.apiCallToGetSubCategoryRelatedMenuDetails(mainCategoryId, subCategoryId)
    }


    private fun clickEvents() {
        binding.tvLeftArrow.setOnClickListener {
            activity?.finish()
        }/*Click on Press the Left Arrow and go back to previous page*/

        binding.floatingMenu.setOnClickListener {

            /* val builder = AlertDialog.Builder(context)
                 builder.setTitle("Choose Category")
             val category = arrayOf("All Day", "Perfect Vegan", "Cereal", "Pan Cakes", "Waffles")
             val checkedItem = 1

             builder.setSingleChoiceItems(category,checkedItem){
                 dialog, which ->
             }

             builder.setPositiveButton("OK") { dialog, which ->
                 // user clicked OK


                 mainCategoryId = "1"
                 subCategoryId = "3"
                // getSuCategoryRelatedMenuData(mainCategoryId!!,subCategoryId!!)
             }



             val dialog = builder.create()
                 dialog.show()*/

            val builder = AlertDialog.Builder(context)
            builder.setTitle("Choose Category")
            val lables: MutableList<String> = ArrayList()
            lables.add("All Day")
            lables.add("Perfect")
            lables.add("Cereal")
            lables.add("Pan Cakes")
            lables.add("Waffles")
            val dataAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
                activity as AppCompatActivity,
                android.R.layout.simple_dropdown_item_1line,
                lables
            )
            builder.setAdapter(dataAdapter) { dialog, which ->
                Toast.makeText(context, "You have selected " + lables[which], Toast.LENGTH_LONG)
                    .show()
            }
            val dialog = builder.create()
            dialog.show()

        }



        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // search(query)
                //  Toast.makeText(context,query,Toast.LENGTH_LONG).show()
                return true
            }

            override fun onQueryTextChange(searchText: String): Boolean {
                // search(newText)
                //Toast.makeText(context,searchText,Toast.LENGTH_LONG).show()
                searchFilter(searchText)
                return true
            }
        })/*getting the Search Text*/



        binding.vegSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                getVegMenuRelatedData(mainCategoryId!!, subCategoryId!!)
            } else {
                getSuCategoryRelatedMenuData(mainCategoryId!!, subCategoryId!!)
            }
        }/*Veg Switch*/
    }


    /*search filter*/
    private fun searchFilter(searchText: String) {
        val filteredMenuList = mutableListOf<SubCategoryMenuData>()
        for (data in menuItemList) {
            if (data.itemName.toLowerCase(Locale.getDefault()).contains(searchText) ||
                data.itemDescription.toLowerCase(Locale.getDefault()).contains(searchText)) {
                filteredMenuList.add(data)
                adapterSubCategoryDescription.menuList = filteredMenuList
                adapterSubCategoryDescription.notifyDataSetChanged()
            }
        }
    }/*get filter the Search Related textview*/


    private fun getVegMenuRelatedData(
        mainCategoryId: String,
        subCategoryId: String,
    ) {
        viewModel.getVegMenuObservable()
            .observe(viewLifecycleOwner, Observer<MenuDetailsResponseDataModel> {
                if (it.statusCode == 400) {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                } else {
                    adapterSubCategoryDescription.menuList =
                        it.subCategoryMenuData.toMutableList()
                    menuItemList = it.subCategoryMenuData.toMutableList()
                    adapterSubCategoryDescription.notifyDataSetChanged()
                }
            })
        viewModel.apiCallVegMenu(mainCategoryId, subCategoryId)
    }/*get Veg Menu related data*/


    override fun addToCartData(itemName: String, itemDescription: String, itemPrice: String, itemMainCategoryName: String, itemSubCategoryName: String,
                               itemFoodType: Boolean, itemQuantity: String, itemId: String, itemImageUrl: String) {
        val addToCartSheet = layoutInflater.inflate(R.layout.item_addtocart_fragment, null)
        val vegOrNonVegImageView = addToCartSheet.findViewById<ImageView>(R.id.vegOrNonVegImageView)
        val tvItemName = addToCartSheet.findViewById<TextView>(R.id.tvItemName)
        val tvItemDescription = addToCartSheet.findViewById<TextView>(R.id.tvDescription)
        val tvAllergyInfo = addToCartSheet.findViewById<TextView>(R.id.tvAllergyInfo)
        val tvItemPrice = addToCartSheet.findViewById<TextView>(R.id.tvPrice)
        val buttonAddToCart = addToCartSheet.findViewById<Button>(R.id.buttonAddToCart)
        if (itemFoodType) {
            vegOrNonVegImageView.setBackgroundResource(R.drawable.veg)
        } else {
            vegOrNonVegImageView.setBackgroundResource(R.drawable.non_veg)
        }

        tvItemName.text = itemName
        tvItemDescription.text = itemDescription
        tvItemPrice.text = resources.getString(R.string.aed) + " " + itemPrice
        dialogAddToCart = BottomSheetDialog(activity as AppCompatActivity)
        dialogAddToCart.setContentView(addToCartSheet)
        dialogAddToCart.show()

        buttonAddToCart.setOnClickListener {
                getAddOnDialog(itemMainCategoryName, itemSubCategoryName, itemFoodType, itemName, itemId, itemQuantity, itemPrice, itemImageUrl, itemDescription)
                // addToCartPostData(itemMainCategoryName, itemSubCategoryName, itemFoodType, itemName, itemId, itemBaseQuantity, itemPrice, itemImageUrl, itemDescription, dialog)
        }
    }


    override fun addOnsButtonClickEvent(itemName: String, itemDescription: String, itemPrice: String, itemMainCategoryName: String,
                                        itemSubCategoryName: String, itemFoodType: Boolean, itemQuantity: String, itemId: String, itemImageUrl: String) {
    }

    private fun getAddOnDialog(itemMainCategoryName: String, itemSubCategoryName: String, itemFoodType: Boolean, itemName: String, itemId: String, itemQuantity: String,
                               itemPrice: String, itemImageUrl: String, itemDescription: String){
        val addOnsSheet = layoutInflater.inflate(R.layout.addons, null)
        val recyclerView = addOnsSheet.findViewById<RecyclerView>(R.id.addonsRecyclerview)
        val tvItemName = addOnsSheet.findViewById<TextView>(R.id.tvItemName)
        tvItemTotalPrice = addOnsSheet.findViewById<TextView>(R.id.tvItemPrice)
        tvAddToCart = addOnsSheet.findViewById<TextView>(R.id.tvAddToCart)
        tvItemName.text = itemName
        itemBasePrice = itemPrice
        tvItemTotalPrice.text = resources.getString(R.string.aed) + " " + itemPrice
        fetchAddonsData(itemId)/*fetch the Addons Methods data*/
        dialogAddOns = BottomSheetDialog(activity as AppCompatActivity)
        addOnsAdapter = AddOnsAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = addOnsAdapter
        dialogAddOns.setContentView(addOnsSheet)
        dialogAddOns.show()
        tvAddToCart.setOnClickListener {
            if (addOnPrice.equals("") || addOnPrice.equals(null)) {
                itemTotal = itemPrice
            } else {
                itemTotal = (itemPrice.toInt() + addOnPrice!!.toInt()).toString()
                Toast.makeText(context,itemTotal.toString(),Toast.LENGTH_LONG).show()
            }
            addToCartPostData(itemMainCategoryName, itemSubCategoryName, itemFoodType, itemName, itemId, itemQuantity, itemTotal!!, itemImageUrl, itemDescription)
        }
    }


    private fun fetchAddonsData(itemId:String){
        viewModel.addOnsObservable().observe(viewLifecycleOwner, Observer<AddOnsDataModel> {
            if (it.statusCode == 400) {
                Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                addOnsAdapter.addOnsList = it.AddOnDetails.toMutableList()
                addOnsAdapter.notifyDataSetChanged()
            }
        })
        viewModel.apiCallAddons(itemId)
    }/*Fetch the addOns data*/

    override fun getSelectedAddOnsValue(amount: String) {
        addOnPrice = amount
        tvItemTotalPrice.text = resources.getString(R.string.aed) + " " + (itemBasePrice!!.toInt() + amount.toInt()).toString()
    }

    private fun addToCartPostData(itemMainCategoryName: String, itemSubCategoryName: String, itemFoodType: Boolean, itemName: String, itemId: String, itemQuantity: String,
                                  itemPrice: String, itemImageUrl: String, itemDescription: String) {
        viewModel.addToCartObservable().observe(viewLifecycleOwner, Observer<CartIncrementOrDecrementResponse> {
                if (it.statusCode == 400) {
                    addOnPrice = null
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                } else if (it.statusCode == 200) {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    addOnPrice = null
                    dialogAddToCart.dismiss()
                    dialogAddOns.dismiss()
                    activity?.finish()
                }
            })
        viewModel.apiCallAddToCart(itemMainCategoryName!!, itemSubCategoryName!!, itemFoodType!!, itemName!!, itemId!!, itemQuantity!!, itemPrice!!, itemImageUrl!!, itemDescription!!, userId!!, itemPrice!!
        )
    }

}



