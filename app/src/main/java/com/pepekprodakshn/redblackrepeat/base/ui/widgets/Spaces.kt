package com.pepekprodakshn.redblackrepeat.base.ui.widgets

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ColumnScope.SpacerHeight(height: Int, modifier: Modifier = Modifier) =
    Spacer(modifier = modifier.height(height.dp))

@Composable
fun RowScope.SpacerWidth(width: Int, modifier: Modifier = Modifier) =
    Spacer(modifier = modifier.width(width.dp))