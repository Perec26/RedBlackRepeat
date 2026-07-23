package com.pepekprodakshn.start.ui

import com.pepekprodakshn.config.AppConfig

const val DEV_SUFFIX = " (dev)"

internal data class StartUiState(val version: String = "0")

internal fun getInitialUiState(config: AppConfig) = StartUiState(
    version = buildString {
        append(config.version)
        if (config.isDebug) append(DEV_SUFFIX)
    }
)
