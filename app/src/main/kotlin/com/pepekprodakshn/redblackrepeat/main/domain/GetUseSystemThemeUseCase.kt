package com.pepekprodakshn.redblackrepeat.main.domain

import com.pepekprodakshn.preferences.PreferencesDataSource
import javax.inject.Inject

class GetUseSystemThemeUseCase @Inject constructor(
    private val preferencesDataSource: PreferencesDataSource,
) {

    fun execute() = preferencesDataSource.getUseSystemThemeFlow()
}
