package com.pepekprodakshn.redblackrepeat.main.ui

import com.pepekprodakshn.redblackrepeat.main.domain.GetUseDarkThemeUseCase
import com.pepekprodakshn.redblackrepeat.main.domain.GetUseSystemThemeUseCase
import com.pepekprodakshn.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUseSystemThemeUseCase: GetUseSystemThemeUseCase,
    private val getUseDarkThemeUseCase: GetUseDarkThemeUseCase,
) : BaseViewModel<MainUiState, MainEvent, MainNavigationEvent>(
    initialState = MainUiState(),
) {

    init {
        subscribeThemeChange()
    }

    override fun onEvent(event: MainEvent) {
    }

    private fun subscribeThemeChange() {
        launch {
            getUseSystemThemeUseCase.execute().collect {
                updateState { updateUseSystemTheme(it) }
            }
        }
        launch {
            getUseDarkThemeUseCase.execute().collect {
                updateState { updateUseDarkTheme(it) }
            }
        }
    }
}
