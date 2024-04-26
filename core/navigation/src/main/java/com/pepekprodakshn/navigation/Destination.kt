package com.pepekprodakshn.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

class Destination(
    val name: String,
    val arguments: List<NamedNavArgument> = emptyList(),
    val content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit,
)

fun NavGraphBuilder.getRoute(destination: Destination) {
    composable(
        route = destination.name + destination.argsToString(),
        arguments = destination.arguments,
        content = destination.content,
    )
}

private fun Destination.argsToString(): String {
    if (arguments.isEmpty()) return ""
    return arguments.joinToString(
        prefix = "/",
        separator = "/",
        transform = { "{${it.name}}" },
    )
}