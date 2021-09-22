package com.utico.fooddelivery.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.utico.fooddelivery.R
import com.utico.fooddelivery.view.fragment.*

class AddFragmentToActivity : AppCompatActivity() {
    var fragmentName:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_details_fragments_to)
        fragmentName = intent.getStringExtra("FragmentName")

        if (fragmentName.equals("OrderDetailsFragment")) {
            supportFragmentManager!!.beginTransaction()
                .add(R.id.fragmentContainer, OrderDetailsFragment(), "OrderDetailsFragment")
                .commit()

        } else if (fragmentName.equals("NotificationDetailsFragment")) {
            supportFragmentManager!!.beginTransaction()
                .add(R.id.fragmentContainer, NotificationDetailsFragment(), "NotificationDetailsFragment")
                .commit()

        } else if(fragmentName.equals("MealsPlanDetailsFragment")){
            supportFragmentManager!!.beginTransaction()
                .add(R.id.fragmentContainer, MealsPlanDetailsFragment(), "MealsPlanDetailsFragment")
                .commit()

        }else if (fragmentName.equals("MealPlanFragment")){
            supportFragmentManager!!.beginTransaction()
                .add(R.id.fragmentContainer, MealPlanFragment(), "MealPlanFragment")
                .commit()
        }else if (fragmentName.equals("HelpSupportFragment")){
            supportFragmentManager!!.beginTransaction()
                .add(R.id.fragmentContainer,HelpSupportFragment(),"HelpSupportFragment")
                .commit()

        }else if (fragmentName.equals("PaymentModeFragment")){
            supportFragmentManager!!.beginTransaction()
                .add(R.id.fragmentContainer,PaymentModeFragment(),"PaymentModeFragment")
                .commit()
        }else if(fragmentName.equals("CardPaymentFragment")){
            supportFragmentManager!!.beginTransaction()
                .add(R.id.fragmentContainer,CardPaymentFragment(),"CardPaymentFragment")
                .commit()

        } else if (fragmentName.equals("BankDetailsFragment")){
            supportFragmentManager!!.beginTransaction()
                .add(R.id.fragmentContainer,BankDetailsFragment(),"BankDetailsFragment")
                .commit()
        }else if (fragmentName.equals("AddCardBankFragment")){
            supportFragmentManager!!.beginTransaction()
                .add(R.id.fragmentContainer,AddCardBankFragment(),"AddCardBankFragment")
                .commit()
        }else if (fragmentName.equals("AddressFragment")){
            supportFragmentManager!!.beginTransaction()
                .add(R.id.fragmentContainer,AddressFragment(),"AddressFragment")
                .commit()
        }else if (fragmentName.equals("AddAddressFragment")){
            supportFragmentManager!!.beginTransaction()
                .add(R.id.fragmentContainer,AddAddressFragment(),"AddAddressFragment")
                .commit()
        } else if(fragmentName.equals("GoogleLocationFragment")){
            supportFragmentManager!!.beginTransaction()
                .add(R.id.fragmentContainer,GoogleLocationFragment(),"GoogleLocationFragment")
                .commit()
        }else if (fragmentName.equals("OrderHistoryFragment")){
            supportFragmentManager!!.beginTransaction()
                .add(R.id.fragmentContainer,OrderHistoryFragment(),"OrderHistoryFragment")
                .commit()

        }else if (fragmentName.equals("MealOrderPackageDetailsFragment")){
            supportFragmentManager!!.beginTransaction()
                .add(R.id.fragmentContainer,MealOrderPackageDetailsFragment(),"MealOrderPackageDetailsFragment")
                .commit()
        }



    }

}