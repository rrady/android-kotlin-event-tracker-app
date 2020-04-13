package com.eventtracker.app.ui.hosts

import com.xwray.groupie.databinding.BindableItem

import com.eventtracker.app.R
import com.eventtracker.app.databinding.HostItemBinding
import com.eventtracker.domain.models.Host

class HostItem(private val host: Host): BindableItem<HostItemBinding>() {
    override fun getLayout(): Int {
        return R.layout.host_item
    }

    override fun bind(viewBinding: HostItemBinding, position: Int) {
        val viewModel = HostViewModel(host)
        viewBinding.viewModel = viewModel
    }
}