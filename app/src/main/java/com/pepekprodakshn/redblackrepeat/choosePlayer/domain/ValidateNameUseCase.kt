package com.pepekprodakshn.redblackrepeat.choosePlayer.domain

import com.pepekprodakshn.redblackrepeat.choosePlayer.domain.model.ValidationResult
import javax.inject.Inject

class ValidateNameUseCase @Inject constructor() {

    fun execute(name: String): ValidationResult {
        if (name.isBlank()) return ValidationResult.Error

        return ValidationResult.Success
    }

}