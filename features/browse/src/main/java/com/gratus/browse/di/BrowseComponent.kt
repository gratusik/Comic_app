package com.gratus.browse.di

import com.gratus.browse.presentation.BrowseCardFragment
import com.gratus.browse.presentation.BrowseFragment
import com.gratus.core.di.component.CoreComponent
import com.gratus.core.di.scopes.FeatureScope
import dagger.Component

@FeatureScope
@Component(
    modules = [
        BrowseModule::class
    ],
    dependencies = [CoreComponent::class]
)
interface BrowseComponent {
    fun inject(browseFragment: BrowseFragment)
    fun inject(browseCardFragment: BrowseCardFragment)
}
