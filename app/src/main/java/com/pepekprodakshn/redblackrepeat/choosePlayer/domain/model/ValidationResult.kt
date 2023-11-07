package com.pepekprodakshn.redblackrepeat.choosePlayer.domain.model

sealed class ValidationResult {
    object Success : ValidationResult()
    object Error : ValidationResult()

    val isError = this !is Success
}