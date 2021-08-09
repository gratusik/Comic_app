package com.gratus.browse.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gratus.browse.presentation.BrowseViewModel
import com.gratus.core.di.factory.ViewModelFactory
import com.gratus.core.di.key.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class BrowseFeatureModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(BrowseViewModel::class)
    internal abstract fun bindComicCardViewModel(viewModel: BrowseViewModel): ViewModel
}
