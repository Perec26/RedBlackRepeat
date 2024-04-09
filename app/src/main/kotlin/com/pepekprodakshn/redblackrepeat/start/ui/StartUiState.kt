package com.pepekprodakshn.redblackrepeat.start.ui

import com.pepekprodakshn.redblackrepeat.BuildConfig

data class StartUiState(
    val version: String = "v. ${BuildConfig.VERSION_NAME}",
)
