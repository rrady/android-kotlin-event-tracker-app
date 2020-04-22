package com.eventtracker.app.di.modules.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

import com.eventtracker.app.di.utils.ViewModelFactory
import com.eventtracker.app.di.utils.ViewModelKey
import com.eventtracker.app.ui.event.EventDetailViewModel
import com.eventtracker.app.ui.host.EventListViewModel
import com.eventtracker.app.ui.host.HostDetailViewModel
import com.eventtracker.app.ui.hostlist.HostListViewModel
import com.eventtracker.app.ui.hostlist.HostViewModel
import com.eventtracker.app.ui.newhost.NewHostViewModel
import com.eventtracker.app.ui.profile.ProfileViewModel

@Module
abstract class ViewModelsModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HostListViewModel::class)
    abstract fun bindHostListViewModel(viewModel: HostListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HostDetailViewModel::class)
    abstract fun bindHostDetailViewModel(viewModel: HostDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewHostViewModel::class)
    abstract fun bindNewHostViewModel(viewModel: NewHostViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel
}