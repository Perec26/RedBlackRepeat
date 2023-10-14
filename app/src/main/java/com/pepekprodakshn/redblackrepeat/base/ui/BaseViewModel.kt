package com.pepekprodakshn.redblackrepeat.base.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

open class BaseViewModel<STATE : Any>(
    initialState: STATE
) : ViewModel() {

    val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()
}