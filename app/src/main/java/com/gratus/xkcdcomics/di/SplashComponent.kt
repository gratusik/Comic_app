package com.gratus.xkcdcomics.di

import com.gratus.core.di.component.CoreComponent
import com.gratus.core.di.scopes.FeatureScope
import com.gratus.xkcdcomics.SplashActivity
import dagger.Component

@FeatureScope
@Component(
    dependencies = [CoreComponent::class]
)
interface SplashComponent {

    fun inject(splashActivity: SplashActivity)
}
