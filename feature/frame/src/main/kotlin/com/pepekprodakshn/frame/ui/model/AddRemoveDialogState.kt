package com.pepekprodakshn.frame.ui.model

data class AddRemoveDialogState(
    val redsOnTable: Int = 15,
    val redsCount: Int = 0,
    val isAdd: Boolean = true,
) {
    val isPlusEnabled = redsCount < (if (isAdd) 15 - redsOnTable else redsOnTable)
}
