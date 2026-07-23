package com.pepekprodakshn.start.ui

import com.pepekprodakshn.config.AppConfig
import com.pepekprodakshn.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class StartViewModel @Inject constructor(private val config: AppConfig) :
    BaseViewModel<StartUiState, StartEvent, StartNavigationEvent>(
        initialState = getInitialUiState(config)
    ) {

    override fun onEvent(event: StartEvent) {
        when (event) {
            StartEvent.OnStartClick -> onNavigationEvent(StartNavigationEvent.OnStartClick)
            StartEvent.OnSettingsClick -> onNavigationEvent(StartNavigationEvent.OnSettingsClick)
        }
    }
}
