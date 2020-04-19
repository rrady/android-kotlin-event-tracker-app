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
import com.eventtracker.app.ui.hostlist.HostListViewModel
import com.eventtracker.app.ui.hostlist.HostViewModel
import com.eventtracker.app.ui.newhost.NewHostViewModel

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
    @ViewModelKey(NewHostViewModel::class)
    abstract fun bindNewHostViewModel(viewModel: NewHostViewModel): ViewModel

//    @Binds
//    @IntoMap
//    @ViewModelKey(HostViewModel::class)
//    abstract fun bindHostViewModel(viewModel: HostViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(EventListViewModel::class)
//    abstract fun bindEventListViewModel(viewModel: EventListViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(EventDetailViewModel::class)
//    abstract fun bindEventDetailViewModel(viewModel: EventDetailViewModel): ViewModel
}