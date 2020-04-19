package com.eventtracker.app.di.modules.ui

import dagger.Module
import dagger.android.ContributesAndroidInjector

import com.eventtracker.app.ui.hostlist.HostListFragment
import com.eventtracker.app.ui.host.EventListFragment
import com.eventtracker.app.ui.host.HostDetailFragment
import com.eventtracker.app.ui.event.EventDetailFragment
import com.eventtracker.app.ui.profile.ProfileFragment

@Module
abstract class FragmentsModule {
    @ContributesAndroidInjector
    abstract fun contributeHostListFragment(): HostListFragment

    @ContributesAndroidInjector
    abstract fun contributeHostDetailFragment(): HostDetailFragment

    @ContributesAndroidInjector
    abstract fun contributeEventListFragment(): EventListFragment

    @ContributesAndroidInjector
    abstract fun contributeEventDetailFragment(): EventDetailFragment

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment
}