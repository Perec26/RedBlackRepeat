package com.pepekprodakshn.convention

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

fun VersionCatalog.findVersionString(name :String) = findVersion(name).get().toString()

fun VersionCatalog.findLibraryString(name :String) = findLibrary(name).get()

fun VersionCatalog.findVersionInt(name :String) = findVersionString(name).toInt()