package com.eventtracker.app.ui.hosts

import com.xwray.groupie.databinding.BindableItem

import com.eventtracker.app.R
import com.eventtracker.app.databinding.HostListItemBinding
import com.eventtracker.domain.models.Host

class HostItem(private val host: Host): BindableItem<HostListItemBinding>() {
    override fun getLayout(): Int {
        return R.layout.host_list_item
    }

    override fun bind(viewBinding: HostListItemBinding, position: Int) {
        val viewModel = HostViewModel(host)
        viewBinding.viewModel = viewModel
    }
}