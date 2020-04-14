package com.eventtracker.app.ui.hostdetail

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

class EventsFragment: Fragment() {
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
        binding.viewModel = viewModel

        adapter.addAll(viewModel.getEvents().map { EventItem(it) })

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(): EventsFragment {
            return EventsFragment()
        }
    }
}