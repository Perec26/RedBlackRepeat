package com.pepekprodakshn.redblackrepeat.di

import com.pepekprodakshn.navigation.SharedRouter
import com.pepekprodakshn.redblackrepeat.navigation.SharedRouterImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SharedRouterModule {

    @Binds
    abstract fun bindSharedRouter(sharedRouterImpl: SharedRouterImpl): SharedRouter
}