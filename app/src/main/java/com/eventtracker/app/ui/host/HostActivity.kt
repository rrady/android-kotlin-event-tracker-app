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

        tabsAdapter = TabsAdapter(supportFragmentManager)
        tabsAdapter.add(hostWrapper.host.name, HostDetailFragment.newInstance(hostWrapper))
        tabsAdapter.add("Events", EventListFragment.newInstance(hostWrapper.host.id))

        viewPager = findViewById(R.id.view_pager)
        viewPager.adapter = tabsAdapter
        val tabs: TabLayout = findViewById(R.id.tab_layout)
        tabs.setupWithViewPager(viewPager)
    }
}