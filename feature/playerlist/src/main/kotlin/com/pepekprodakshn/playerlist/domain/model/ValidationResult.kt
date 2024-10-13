package com.pepekprodakshn.playerlist.domain.model

internal sealed class ValidationResult {
    data object Success : ValidationResult()
    data object Error : ValidationResult()
}