package com.eventtracker.app.ui.eventdetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.eventtracker.app.R
import com.eventtracker.app.databinding.ActivityEventDetailBinding

class EventDetailActivity: AppCompatActivity() {
    private lateinit var binding: ActivityEventDetailBinding
    private lateinit var viewModel: EventDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_event_detail)
        viewModel = ViewModelProvider(this).get(EventDetailViewModel::class.java)
        binding.viewModel = viewModel
    }
}