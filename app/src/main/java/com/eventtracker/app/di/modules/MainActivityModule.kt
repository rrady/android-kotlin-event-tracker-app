package com.eventtracker.app.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector

import com.eventtracker.app.ui.MainActivity
import com.eventtracker.app.ui.hostlist.HostListFragment

@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeHostListFragment(): HostListFragment
}