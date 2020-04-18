package com.eventtracker.app.di.modules.ui

import dagger.Module
import dagger.android.ContributesAndroidInjector

import com.eventtracker.app.ui.MainActivity
import com.eventtracker.app.ui.host.HostActivity

@Module
abstract class ActivitiesModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeHostActivity(): HostActivity
}