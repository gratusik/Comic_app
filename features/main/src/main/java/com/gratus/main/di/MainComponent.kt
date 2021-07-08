package com.gratus.main.di

import com.gratus.core.di.component.CoreComponent
import com.gratus.core.di.scopes.FeatureScope
import com.gratus.main.presentation.MainActivity
import dagger.Component

@FeatureScope
@Component(
    dependencies = [CoreComponent::class]
)
interface MainComponent {

    fun inject(mainActivity: MainActivity)
}
