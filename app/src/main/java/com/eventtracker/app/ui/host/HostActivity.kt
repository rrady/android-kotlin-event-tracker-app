package com.eventtracker.app.ui.host

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager

import com.eventtracker.app.R
import com.eventtracker.app.ui.hostlist.HostListFragment
import com.eventtracker.app.wrappers.HostWrapper
import com.google.android.material.tabs.TabLayout

class HostActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager
    private lateinit var tabsAdapter: TabsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.host_activity)

        val hostWrapper = intent.getParcelableExtra<HostWrapper>(HostListFragment.HOST_KEY)

        val hostDetailFragment = HostDetailFragment.newInstance()
        val hostDetailFragmentArguments = Bundle()
        hostDetailFragmentArguments.putParcelable(HOST_KEY, hostWrapper)
        hostDetailFragment.arguments = hostDetailFragmentArguments

        val eventListFragment = EventListFragment.newInstance()
        val eventFragmentArguments = Bundle()
        eventFragmentArguments.putString(HOST_ID_KEY, hostWrapper.host.id)
        eventListFragment.arguments = eventFragmentArguments

        tabsAdapter = TabsAdapter(supportFragmentManager)
        tabsAdapter.add(hostWrapper.host.name, hostDetailFragment)
        tabsAdapter.add("Events", eventListFragment)

        viewPager = findViewById(R.id.view_pager)
        viewPager.adapter = tabsAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
    }

    companion object {
        val HOST_KEY = "HOST_KEY"
        val HOST_ID_KEY = "HOST_ID_KEY"
    }
}