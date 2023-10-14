package com.pepekprodakshn.redblackrepeat.start.ui

import com.pepekprodakshn.redblackrepeat.base.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor() :
    BaseViewModel<StartUiState>(initialState = StartUiState()) {
}