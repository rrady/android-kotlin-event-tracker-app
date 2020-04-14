package com.eventtracker.app.ui.hostdetail

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> HostFragment.newInstance()
            1 -> EventsFragment.newInstance()
            else -> Fragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "Host"
            1 -> "Events"
            else -> ""
        }
    }

    override fun getCount(): Int {
        return 2
    }
}