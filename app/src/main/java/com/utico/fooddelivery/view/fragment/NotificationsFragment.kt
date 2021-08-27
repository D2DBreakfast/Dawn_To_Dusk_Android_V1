package com.utico.fooddelivery.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.utico.fooddelivery.adapter.AdapterNotification
import com.utico.fooddelivery.databinding.FragmentNotificationsBinding
import com.utico.fooddelivery.model.NotificationList
import com.utico.fooddelivery.viewmodel.NotificationsViewModel

class NotificationsFragment : Fragment() {

    private lateinit var viewModel: NotificationsViewModel
    private var _binding: FragmentNotificationsBinding? = null
    private lateinit var adapterNotification: AdapterNotification

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(NotificationsViewModel::class.java)
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setUpUi()
        initViewModel()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setUpUi(){
        val recyclerView = binding.notificationRecyclerview
           recyclerView.layoutManager = LinearLayoutManager(activity)
        val decoration = DividerItemDecoration(activity,DividerItemDecoration.VERTICAL)
            recyclerView.addItemDecoration(decoration)
            adapterNotification = AdapterNotification()
            recyclerView.adapter = adapterNotification
    }

    fun initViewModel(){
        viewModel.getNoticationObserable().observe(viewLifecycleOwner, Observer<NotificationList> {
        adapterNotification.notificationList = it.data.toMutableList()
        adapterNotification.notifyDataSetChanged()
        })
        viewModel.makeApiCallNotificationList()
    }
}