package com.eventtracker.app.di.modules.ui

import dagger.Module
import dagger.android.ContributesAndroidInjector

import com.eventtracker.app.ui.MainActivity
import com.eventtracker.app.ui.host.HostActivity
import com.eventtracker.app.ui.newhost.NewHostActivity
import com.eventtracker.app.ui.signin.SignInActivity

@Module
abstract class ActivitiesModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeSignInActivity(): SignInActivity

    @ContributesAndroidInjector
    abstract fun contributeHostActivity(): HostActivity

    @ContributesAndroidInjector
    abstract fun contributeNewHostActivity(): NewHostActivity
}