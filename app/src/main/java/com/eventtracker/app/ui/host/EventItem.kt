package com.eventtracker.app.ui.host

import com.xwray.groupie.databinding.BindableItem

import com.eventtracker.app.R
import com.eventtracker.app.databinding.EventListItemBinding
import com.eventtracker.domain.models.Event

class EventItem(private val event: Event): BindableItem<EventListItemBinding>() {
    override fun getLayout(): Int {
        return R.layout.event_list_item
    }

    override fun bind(viewBinding: EventListItemBinding, position: Int) {

    }
}