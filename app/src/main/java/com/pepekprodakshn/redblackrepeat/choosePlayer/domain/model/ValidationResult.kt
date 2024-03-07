package com.pepekprodakshn.redblackrepeat.choosePlayer.domain.model

sealed class ValidationResult {
    object Success : ValidationResult()
    object Error : ValidationResult()
}