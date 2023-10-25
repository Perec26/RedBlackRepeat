package com.pepekprodakshn.redblackrepeat.base.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<STATE : Any, EVENT : Any>(
    initialState: STATE,
) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()
    val viewState = state.value

    abstract fun onEvent(event: EVENT)

    protected fun updateState(block: STATE.() -> STATE) {
        _state.value = block.invoke(_state.value)
    }

}