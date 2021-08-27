package com.utico.fooddelivery.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.utico.fooddelivery.R
import com.utico.fooddelivery.viewmodel.HelpSupportViewModel

class HelpSupportFragment : Fragment() {

    companion object {
        fun newInstance() = HelpSupportFragment()
    }

    private lateinit var viewModel: HelpSupportViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.help_support_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HelpSupportViewModel::class.java)
        // TODO: Use the ViewModel
    }

}