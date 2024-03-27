package com.pepekprodakshn.redblackrepeat.start.ui

import com.pepekprodakshn.redblackrepeat.base.ui.BaseViewModel
import com.pepekprodakshn.redblackrepeat.navigation.Destinations
import com.pepekprodakshn.redblackrepeat.navigation.RBRNavController
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    private val navHostController: RBRNavController,
) :
    BaseViewModel<StartUiState, StartEvent>(initialState = StartUiState()) {

    override fun onEvent(event: StartEvent) {
        when (event) {
            StartEvent.ButtonClick -> navHostController.navigateTo(Destinations.ChoosePlayer)
        }
    }
}