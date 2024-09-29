package com.pepekprodakshn.start.ui

import com.pepekprodakshn.navigation.SharedRouter
import com.pepekprodakshn.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class StartViewModel @Inject constructor(
    private val sharedRouter: SharedRouter,
) : BaseViewModel<StartUiState, StartEvent>(initialState = StartUiState()) {

    override fun onEvent(event: StartEvent) {
        when (event) {
            StartEvent.ButtonClick -> sharedRouter.navigateToPlayerList()
        }
    }
}