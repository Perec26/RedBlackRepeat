package com.pepekprodakshn.redblackrepeat.di

import androidx.navigation.NavController
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

abstract class NavigationModule {

    fun provideNavController() = NavController

}