package com.utico.fooddelivery.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.utico.fooddelivery.R
import com.utico.fooddelivery.viewmodel.MealsFoodOrderHistoryDetailsViewModel

class MealsFoodOrderHistoryDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = MealsFoodOrderHistoryDetailsFragment()
    }

    private lateinit var viewModel: MealsFoodOrderHistoryDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.meals_food_order_history_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MealsFoodOrderHistoryDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}

/*1.Skip option is not required
* 2.change the name like package
* 3.change the toggle btnAddToCart name is veg
* 4.food category default is All
* 5.multiple selection in category selection
* 6.All option search is not required
* 7.for allocate for 5day's
* 8.disable the friday and sat and calendar option
* 9.change the label for the upcoming meals to daily variants
* 10.Apartment no, tower no,cluster,community*/