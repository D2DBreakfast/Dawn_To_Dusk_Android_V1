package com.utico.fooddelivery.view.fragment

import android.app.DatePickerDialog
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.utico.fooddelivery.R
import com.utico.fooddelivery.`interface`.CallbackSubscription
import com.utico.fooddelivery.adapter.SubscriptionDetailsAdapter
import com.utico.fooddelivery.adapter.SubscriptionPackagePlansAdapter
import com.utico.fooddelivery.databinding.SubscriptionDetailsFragmentBinding
import com.utico.fooddelivery.model.OrderPlacedResponse
import com.utico.fooddelivery.model.SubscriptionPlansResponseModel
import com.utico.fooddelivery.model.SubscriptionUpcomingMealResponseModel
import com.utico.fooddelivery.viewmodel.SubscriptionDetailsViewModel
import java.util.*

class SubscriptionDetailsFragment : Fragment(),CallbackSubscription {
    private lateinit var viewModel: SubscriptionDetailsViewModel
    private lateinit var binding: SubscriptionDetailsFragmentBinding
    private lateinit var subscriptionDetailsAdapter: SubscriptionDetailsAdapter
    private  var subscriptionPackagePlansAdapter: SubscriptionPackagePlansAdapter? = null
    private  var sharedPreferences:SharedPreferences? = null
    private var userId:String? = null
    private var beforeVatPlanPrice:String? = null
    private var afterVatPlanPrice:String? = null
    private var subscriptiontype:String? = null
    private var planStartDate:String? = null
    private var villaNumber:String? = null
    private var landMark:String? = null
    private var subscriptionPlanNumberOfDay:String? = null
    private var vatCalculation:Double =0.0
    private lateinit var tvBeforeVat:TextView
    private lateinit var tvVat:TextView
    private lateinit var tvAfterVat:TextView
    private var itemPrice:String? = null
    private var packagePlanSharedPreferences: SharedPreferences? = null

    private  var subscriptionDescription:String? = null
    private  var subscriptionTypeId: String? = null
    private  var subscriptionImage: String? = null
    private  var subscriptionLeastAmount: String? = null
    private  var subscriptionType: String? = null
    private  var subscriptionPlanId:String? = null

    companion object {
        fun newInstance() = SubscriptionDetailsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = SubscriptionDetailsFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(SubscriptionDetailsViewModel::class.java)
        sharedPreferences = context?.getSharedPreferences(resources.getString(R.string.registration_details_sharedPreferences),Context.MODE_PRIVATE)
        userId = sharedPreferences?.getString("userId","")
        packagePlanSharedPreferences =context?.getSharedPreferences(context?.resources?.getString(R.string.subscription_plan_details_sharedPreferences),Context.MODE_PRIVATE)
        subscriptionDescription = packagePlanSharedPreferences?.getString("subscriptionDescription","")
        subscriptionTypeId = packagePlanSharedPreferences?.getString("subscriptionId","")
        subscriptionImage = packagePlanSharedPreferences?.getString("subscriptionImage","")
        subscriptionLeastAmount = packagePlanSharedPreferences?.getString("subscriptionLeastAmount","")
        subscriptionType = packagePlanSharedPreferences?.getString("subscriptionType","")

        val view = binding.root
        initAdapter()
        upcomingMeals()
        mealPlans()
        commonClickEventsWithSetUpUiData()
        return view
    }

    private fun initAdapter() {
        /*RecyclerView List of the Days wise food exe: Upcoming Meals*/
        val upcomingMealsRecyclerView = binding.upcomingMealsRecyclerView
        upcomingMealsRecyclerView.layoutManager = LinearLayoutManager(activity)
        subscriptionDetailsAdapter = SubscriptionDetailsAdapter()
        upcomingMealsRecyclerView.adapter = subscriptionDetailsAdapter

        /*Package Plan RecyclerView*/
        val packagePlansRecyclerView = binding.packagePlansRecyclerView
        packagePlansRecyclerView.layoutManager = LinearLayoutManager(activity)
        subscriptionPackagePlansAdapter = SubscriptionPackagePlansAdapter(this)
        packagePlansRecyclerView.adapter = subscriptionPackagePlansAdapter
    }

    override fun subscriptionType(
        subscriptionDescription: String, subscriptionId: String, subscriptionImage: String,
        subscriptionLeastAmount: String,
        subscriptionType: String) {
        Toast.makeText(context,subscriptionType,Toast.LENGTH_SHORT).show()
    }


    override fun subscriptionPlan( numberOfPlanDays: String, price: String, subscriptionType: String,
                                   subscriptionId: String) {
       //Toast.makeText(context,numberOfPlanDays,Toast.LENGTH_SHORT).show()
         subscriptionPlanNumberOfDay = numberOfPlanDays
         vatCalculation = (price.toInt())*0.05
         afterVatPlanPrice = (price.toInt()+vatCalculation).toString()
         subscriptiontype = subscriptionType
         itemPrice = price
    }

    private fun upcomingMeals(){
     viewModel.subscriptionUpcomingMealObservable().observe(viewLifecycleOwner, Observer<SubscriptionUpcomingMealResponseModel> {
         if (it.statusCode == 400){
             Toast.makeText(context,it.message,Toast.LENGTH_LONG).show()
         }else {
             subscriptionDetailsAdapter.upcomingMealsList = it.upcomingMeals.toMutableList()
             subscriptionDetailsAdapter.notifyDataSetChanged()
         }
     })
        Toast.makeText(context,subscriptionTypeId,Toast.LENGTH_LONG).show()

        viewModel.apiCallUpcomingMeals(subscriptionTypeId!!)
    }

    private fun mealPlans(){
        viewModel.subscriptionPlanObservable().observe(viewLifecycleOwner, Observer<SubscriptionPlansResponseModel> {
            subscriptionPackagePlansAdapter?.packageTypeList = it.subscriptionPlans.toMutableList()
            subscriptionPackagePlansAdapter?.notifyDataSetChanged()
        })
        viewModel.apiCallPlan(subscriptionTypeId!!)
    }/*get the Meals Plan like the 7 Days 15Days and 30 Days*/


    private fun commonClickEventsWithSetUpUiData(){
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val lnrCalendar = binding.lnrCalendar
        val tvDate = binding.tvDate

       lnrCalendar.setOnClickListener {
            val datePicker = DatePickerDialog(activity as Context, DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->
                    tvDate.setText(""+ dayOfMonth + "/" + month + "/" + year)
                    planStartDate =(""+ year + "-" + month + "-" + dayOfMonth)
            },year,month,day)
            datePicker.getDatePicker().setMinDate(calendar.getTimeInMillis());
            datePicker.show()
       }/*This is the Calendar events*/

        binding.tvPlanTitle.text = subscriptionType
        binding.tvPlanName.text = subscriptionType
        binding.tvPlanDescription.text =subscriptionDescription

        binding.buttonSubscribe.setOnClickListener {
            if (subscriptionPlanNumberOfDay.equals("") || subscriptionPlanNumberOfDay.equals(null)){
               Toast.makeText(context,resources.getString(R.string.choose_subscription_plan),Toast.LENGTH_LONG).show()
            }else if (planStartDate.equals("") || planStartDate.equals(null)) {
                Toast.makeText(context,resources.getString(R.string.select_plan_start_date),Toast.LENGTH_LONG).show()
            }else{
                subscriptionBottomSheetDialog()
            }
        }/*Click events for the Subscription*/
    }




    private fun subscriptionBottomSheetDialog(){
        val subscriptionBottomSheetDialogLayout = layoutInflater.inflate(R.layout.subscription_dialog,null)
            val editTextVillaNo = subscriptionBottomSheetDialogLayout.findViewById<EditText>(R.id.edtVillaNo)
            val editTextEnterLandMark = subscriptionBottomSheetDialogLayout.findViewById<EditText>(R.id.edtLandMark)
            val btnPay = subscriptionBottomSheetDialogLayout.findViewById<ExtendedFloatingActionButton>(R.id.btnFloatPay)
            val tvPlanPrice = subscriptionBottomSheetDialogLayout.findViewById<TextView>(R.id.tvPlanPrice)
            val tvSubscriptionType = subscriptionBottomSheetDialogLayout.findViewById<TextView>(R.id.tvSubscriptionType)
            val tvPlans = subscriptionBottomSheetDialogLayout.findViewById<TextView>(R.id.tvPlans)
               tvBeforeVat = subscriptionBottomSheetDialogLayout.findViewById<EditText>(R.id.beforeVatTotal)
               tvVat = subscriptionBottomSheetDialogLayout.findViewById<EditText>(R.id.tvVatTotal)
               tvAfterVat = subscriptionBottomSheetDialogLayout.findViewById<EditText>(R.id.tvAfterVatTotal)
               tvPlanPrice.text = resources.getString(R.string.aed)+" "+ afterVatPlanPrice
               tvSubscriptionType.text = subscriptiontype
               tvBeforeVat.text = itemPrice
               tvPlans.text =subscriptionPlanNumberOfDay +" "+ resources.getString(R.string.days_plan)
               tvVat.text = vatCalculation.toString()
               tvAfterVat.text = afterVatPlanPrice
               btnPay.text =resources.getString(R.string.pay_aed)+" "+afterVatPlanPrice


            val bottomDialog = BottomSheetDialog(activity as AppCompatActivity)
                bottomDialog.setContentView(subscriptionBottomSheetDialogLayout)
                bottomDialog.show()

        btnPay.setOnClickListener {
            villaNumber = editTextVillaNo.text.toString()
            landMark = editTextEnterLandMark.text.toString()
            if (villaNumber.equals("") || villaNumber.equals(null)) {
                Toast.makeText(context, resources.getString(R.string.please_enter_villa_no), Toast.LENGTH_LONG).show()
            } else if (landMark.equals("") || landMark.equals(null)) {
                Toast.makeText(context, resources.getString(R.string.please_enter_land_mark), Toast.LENGTH_LONG).show()
            } else {
                viewModel.placeSubscriptionObservable()
                    .observe(viewLifecycleOwner, Observer<OrderPlacedResponse> {
                        if (it.statusCode == 400) {
                            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                            activity?.finish()
                        }
                    })
                viewModel.apiCallPlaceSubscriptionPlan(userId!!, "2", villaNumber!!, landMark!!, afterVatPlanPrice!!,
                                                        "Subscription", subscriptionPlanNumberOfDay!!, planStartDate!!, subscriptiontype!!)

            }
        }

    }

}