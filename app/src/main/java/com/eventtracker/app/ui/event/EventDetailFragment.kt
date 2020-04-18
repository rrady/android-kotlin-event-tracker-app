package com.eventtracker.app.ui.event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import com.eventtracker.app.R
import com.eventtracker.app.databinding.FragmentEventDetailBinding

class EventDetailFragment: Fragment(), OnMapReadyCallback {
    private lateinit var binding: FragmentEventDetailBinding
    private lateinit var viewModel: EventDetailViewModel
    private lateinit var gMap: GoogleMap

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

        initGoogleMap()
        return binding.root
    }

    override fun onMapReady(googleMap: GoogleMap) {
        gMap = googleMap

        val eventLocation = LatLng(-34.0, 151.0)
        gMap.addMarker(MarkerOptions().position(eventLocation).title("Event name"))
        gMap.moveCamera(CameraUpdateFactory.newLatLng(eventLocation))
    }

    private fun initGoogleMap() {
        binding.mapView.getMapAsync(this)
    }
}