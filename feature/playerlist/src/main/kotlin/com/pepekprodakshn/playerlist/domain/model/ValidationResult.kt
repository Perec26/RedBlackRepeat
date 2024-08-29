package com.pepekprodakshn.playerlist.domain.model

sealed class ValidationResult {
    data object Success : ValidationResult()
    data object Error : ValidationResult()
}