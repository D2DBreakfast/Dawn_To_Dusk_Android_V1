package com.utico.fooddelivery.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.utico.fooddelivery.adapter.AdapterPackageOrderHistory
import com.utico.fooddelivery.databinding.OrderHistoryFragmentBinding
import com.utico.fooddelivery.viewmodel.OrderHistoryViewModel

class OrderHistoryFragment : Fragment() {
    private lateinit var binding: OrderHistoryFragmentBinding

    companion object {
        fun newInstance() = OrderHistoryFragment()
    }

    private lateinit var viewModel: OrderHistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = OrderHistoryFragmentBinding.inflate(inflater,container,false)
        val view:View = binding.root
        setUpAdapter()

        val btn_completed = binding.btnRunning
        val btn_running = binding.btnRunning
        val btn_togglegroup = binding.btnToggleGroup


        /*Click event for the completed order*/
            btn_completed.setOnClickListener {
                setUpAdapter()

            }

        /*Click event for the running order*/
            btn_running.setOnClickListener {
               setUpAdapter()
            }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OrderHistoryViewModel::class.java)
        // TODO: Use the ViewModel
    }

    fun setUpAdapter(){
        val recyclerView = binding.orderHistoryRecyclerView
            recyclerView.layoutManager = LinearLayoutManager(activity)
            recyclerView.adapter = AdapterPackageOrderHistory()
    }


}