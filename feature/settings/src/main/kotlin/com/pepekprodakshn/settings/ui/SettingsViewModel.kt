package com.pepekprodakshn.settings.ui

import com.pepekprodakshn.settings.domain.GetUseDarkThemeUseCase
import com.pepekprodakshn.settings.domain.GetUseSystemThemeUseCase
import com.pepekprodakshn.settings.domain.UpdateUseDarkThemeUseCase
import com.pepekprodakshn.settings.domain.UpdateUseSystemThemeUseCase
import com.pepekprodakshn.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class SettingsViewModel @Inject constructor(
    private val updateUseSystemThemeUseCase: UpdateUseSystemThemeUseCase,
    private val updateUseDarkThemeUseCase: UpdateUseDarkThemeUseCase,
    private val getUseSystemThemeUseCase: GetUseSystemThemeUseCase,
    private val getUseDarkThemeUseCase: GetUseDarkThemeUseCase,
) : BaseViewModel<SettingsUiState, SettingsEvent, SettingsNavigationEvent>(
    initialState = SettingsUiState(),
) {

    init {
        setCurrentThemeSettings()
    }

    override fun onEvent(event: SettingsEvent) {
        when (event) {
            SettingsEvent.OnBackPressed -> onNavigationEvent(SettingsNavigationEvent.OnBackPress)
            SettingsEvent.OnUseDarkThemeClick -> onUseDarkThemeClick()
            SettingsEvent.OnUseSystemThemeClick -> onUseSystemThemeClick()
        }
    }

    private fun setCurrentThemeSettings() {
        launch {
            val useSystemTheme = getUseSystemThemeUseCase.execute()
            val useDarkTheme = getUseDarkThemeUseCase.execute()
            updateState { updateUseSystemTheme(useSystemTheme) }
            updateState { updateUseDarkTheme(useDarkTheme) }
        }
    }

    private fun onUseSystemThemeClick() {
        launch {
            val useSystemTheme = !state.value.useSystemTheme
            updateUseSystemThemeUseCase.execute(useSystemTheme)
            updateState { updateUseSystemTheme(useSystemTheme) }
        }
    }

    private fun onUseDarkThemeClick() {
        launch {
            val useDarkTheme = !state.value.useDarkTheme
            updateUseDarkThemeUseCase.execute(useDarkTheme)
            updateState { updateUseDarkTheme(useDarkTheme) }
        }
    }
}