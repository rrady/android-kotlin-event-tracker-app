package com.eventtracker.app.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

import com.eventtracker.app.di.utils.ViewModelFactory
import com.eventtracker.app.di.utils.ViewModelKey
import com.eventtracker.app.ui.hostlist.HostListViewModel

@Module
abstract class ViewModelsModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HostListViewModel::class)
    abstract fun hostListViewModel(viewModel: HostListViewModel): ViewModel
}