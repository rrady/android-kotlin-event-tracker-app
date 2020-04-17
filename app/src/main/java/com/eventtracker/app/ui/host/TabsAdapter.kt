package com.eventtracker.app.ui.host

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TabsAdapter(fm: FragmentManager): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    data class Tab(val title: String, val fragment: Fragment)
    private val tabs = mutableListOf<Tab>()

    fun add(title: String, fragment: Fragment) {
        tabs.add(Tab(title, fragment))
    }

    override fun getItem(position: Int): Fragment {
        return tabs[position].fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabs[position].title
    }

    override fun getCount(): Int {
        return tabs.size
    }
}