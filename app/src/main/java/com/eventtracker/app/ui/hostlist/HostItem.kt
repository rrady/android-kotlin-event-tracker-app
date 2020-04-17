package com.eventtracker.app.ui.hostlist

import com.xwray.groupie.databinding.BindableItem

import com.eventtracker.app.R
import com.eventtracker.app.databinding.HostListItemBinding
import com.eventtracker.domain.models.Host

class HostItem(private val _host: Host): BindableItem<HostListItemBinding>() {
    override fun getLayout(): Int {
        return R.layout.host_list_item
    }

    override fun bind(viewBinding: HostListItemBinding, position: Int) {
        val viewModel = HostViewModel(_host)
        viewBinding.viewModel = viewModel
    }

    val host: Host
        get() = _host
}