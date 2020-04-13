package com.eventtracker.app.ui.hosts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

import com.eventtracker.app.R
import com.eventtracker.app.databinding.ActivityHostListBinding

class HostListActivity: AppCompatActivity() {
    private lateinit var binding: ActivityHostListBinding
    private lateinit var viewModel: HostListViewModel
    private var adapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_host_list)
        binding.recyclerViewHost.adapter = adapter
        viewModel = ViewModelProvider(this).get(HostListViewModel::class.java)
        binding.viewModel = viewModel

        populateHosts()
    }

    private fun populateHosts() {
        viewModel.getHosts().forEach {
            adapter.add(HostItem(it))
        }
    }
}