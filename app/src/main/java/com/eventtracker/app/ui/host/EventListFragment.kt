package com.eventtracker.app.ui.host

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.eventtracker.app.R
import com.eventtracker.app.databinding.FragmentEventListBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class EventListFragment: Fragment() {
    private lateinit var binding: FragmentEventListBinding
    private lateinit var viewModel: EventListViewModel
    private var adapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_event_list, container, false)
        binding.recyclerViewEvents.adapter = adapter
        viewModel = ViewModelProvider(this).get(EventListViewModel::class.java)
        viewModel.hostId = arguments?.getString(HOST_ID_KEY)!!
        binding.viewModel = viewModel

        return binding.root
    }

    companion object {
        const val HOST_ID_KEY = "HOST_ID_KEY"

        @JvmStatic
        fun newInstance(hostId: String): EventListFragment {
            val instance =  EventListFragment()

            val args = Bundle()
            args.putString(HOST_ID_KEY, hostId)
            instance.arguments = args

            return instance
        }
    }
}