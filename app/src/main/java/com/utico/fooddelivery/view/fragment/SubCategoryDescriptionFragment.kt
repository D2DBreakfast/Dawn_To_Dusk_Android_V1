package com.utico.fooddelivery.view.fragment
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.utico.fooddelivery.R
import com.utico.fooddelivery.adapter.AdapterSubCategoryDescription
import com.utico.fooddelivery.databinding.SubCategoryDescriptionFragmentBinding
import com.utico.fooddelivery.model.SubCategoryMenuDetailsModel
import com.utico.fooddelivery.util.toast
import com.utico.fooddelivery.viewmodel.SubCategoryDescriptionViewModel

class SubCategoryDescriptionFragment : Fragment() {
    private lateinit var binding: SubCategoryDescriptionFragmentBinding
    private lateinit var viewModel: SubCategoryDescriptionViewModel
    private lateinit var adapterSubCategoryDescription: AdapterSubCategoryDescription
    private lateinit var sharedPreferences:SharedPreferences
    private var mainCategoryId:String? = null
    private var subCategoryId:String? = null
    private var category = arrayOf("All Day", "Perfect Vegan", "Cereal", "Pan Cakes", "Waffles")
    lateinit var adapter:ArrayAdapter<String>
    lateinit var listView: ListView
    lateinit var alertDialog: AlertDialog.Builder
    lateinit var dialog: AlertDialog


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = SubCategoryDescriptionFragmentBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this).get(SubCategoryDescriptionViewModel::class.java)
        sharedPreferences =(activity as AppCompatActivity).getSharedPreferences(resources.getString(R.string.subCategory_sharedPreference_data),Context.MODE_PRIVATE)
       mainCategoryId = sharedPreferences.getString("mainCategoryId","")
       subCategoryId = sharedPreferences.getString("subCategoryId","")
        val view:View = binding.root
        initAdapter()
        getSuCategoryRelatedMenuData(mainCategoryId!!,subCategoryId!!)
        clickEvent()
        return view
    }

    private fun initAdapter(){
        val recyclerView = binding.CategoryDescriptionRecyclerview
            recyclerView.layoutManager = LinearLayoutManager(activity)
            adapterSubCategoryDescription = AdapterSubCategoryDescription()
            recyclerView.adapter = adapterSubCategoryDescription

    }

    private fun getSuCategoryRelatedMenuData(mainCategoryId:String,subCategoryId:String){
        viewModel.subCategoryRelatedMenuDetailsObservable().observe(viewLifecycleOwner, Observer<SubCategoryMenuDetailsModel> {
            if (it.statusCode == 400){
                val tvErrorMessage = binding.tvErrorMessage
                    tvErrorMessage.visibility = View.VISIBLE
                    tvErrorMessage.text = it.message
            } else{
                adapterSubCategoryDescription.menuList = it.subCategoryMenuData.data.toMutableList()
            adapterSubCategoryDescription.notifyDataSetChanged()
        }
        })
        viewModel.apiCallToGetSubCategoryRelatedMenuDetails(mainCategoryId,subCategoryId)
    }

    private fun clickEvent(){
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
            val dataAdapter: ArrayAdapter<String> = ArrayAdapter<String>(activity as AppCompatActivity, android.R.layout.simple_dropdown_item_1line, lables)
                 builder.setAdapter(dataAdapter) { dialog, which ->
                Toast.makeText(context, "You have selected " + lables[which], Toast.LENGTH_LONG).show()
            }
            val dialog = builder.create()
            dialog.show()
        }
    }

}


