package com.eventtracker.app.ui.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.eventtracker.app.R
import com.eventtracker.app.databinding.FragmentEventDetailBinding

class EventDetailFragment: Fragment() {
    private lateinit var binding: FragmentEventDetailBinding
    private lateinit var viewModel: EventDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_event_detail, container, false)
        viewModel = ViewModelProvider(this).get(EventDetailViewModel::class.java)
        binding.viewModel = viewModel

        return binding.root
    }
}