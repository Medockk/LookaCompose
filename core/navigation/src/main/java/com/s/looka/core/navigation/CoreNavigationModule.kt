package com.s.looka.core.navigation

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class CoreNavigationModule {

    @Singleton
    @Binds
    abstract fun bindNavigator(impl: NavigatorImpl): Navigator
}