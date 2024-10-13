package com.pepekprodakshn.playerlist.domain

import com.pepekprodakshn.playerlist.domain.model.ValidationResult
import javax.inject.Inject

internal class ValidateNameUseCase @Inject constructor() {

    fun execute(name: String): ValidationResult {
        if (name.isBlank()) return ValidationResult.Error

        return ValidationResult.Success
    }
}