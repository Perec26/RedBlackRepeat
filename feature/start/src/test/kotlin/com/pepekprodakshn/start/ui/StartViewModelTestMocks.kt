package com.pepekprodakshn.start.ui

import com.pepekprodakshn.config.AppConfig

internal val testConfig = object : AppConfig {
    override val isDebug = true
    override val version = "0.0.1"
}

internal fun testStartViewModel(config: AppConfig = testConfig) = StartViewModel(config)
