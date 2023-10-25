package com.pepekprodakshn.redblackrepeat.newPlayer.ui

import com.pepekprodakshn.redblackrepeat.base.ui.BaseViewModel
import javax.inject.Inject

class NewPlayerViewModel @Inject constructor():
    BaseViewModel<NewPlayerViewState, NewPlayerEvent>(
        initialState = NewPlayerViewState()
    ) {

    override fun onEvent(event: NewPlayerEvent) {

    }
}