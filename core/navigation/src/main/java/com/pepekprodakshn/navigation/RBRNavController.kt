package com.pepekprodakshn.navigation

import android.content.Context
import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.DialogNavigator

class RBRNavController(context: Context) : NavHostController(context) {

    init {
        navigatorProvider.addNavigator(ComposeNavigator())
        navigatorProvider.addNavigator(DialogNavigator())
    }

    fun navigateTo(destination: Destination) {
        navigate(destination.name)
    }

    fun navigateTo(destination: Destination, vararg args: Any) {
        navigate(destination.name + args.joinToString(prefix = "/", separator = "/"))
    }

    fun getStringArg(name: String): String? {
        return currentBackStackEntry?.arguments?.getString(name)
    }

    fun getIntArg(name: String): Int? {
        return currentBackStackEntry?.arguments?.getInt(name)
    }
}