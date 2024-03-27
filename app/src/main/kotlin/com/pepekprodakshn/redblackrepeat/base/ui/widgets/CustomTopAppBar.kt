package com.pepekprodakshn.redblackrepeat.base.ui.widgets

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pepekprodakshn.redblackrepeat.ui.theme.RBRTypography
import com.pepekprodakshn.redblackrepeat.ui.theme.RedBlackRepeatTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    title: String,
    onNavigationClick: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        title = { Title(title = title) },
        actions = actions,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        navigationIcon = {
            onNavigationClick?.let {
                IconButton(onClick = it) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = ""
                    )
                }
            }
        }
    )
}

@Composable
private fun Title(title: String) = Text(
    text = title,
    style = RBRTypography.titleLarge
)

@Preview(showBackground = true)
@Composable
private fun CustomTopAppBarPreview() {
    CustomTopAppBarPreviewContent()
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CustomTopAppBarPreviewDark() {
    CustomTopAppBarPreviewContent()
}

@Composable
private fun CustomTopAppBarPreviewContent() {
    RedBlackRepeatTheme {
        Column(Modifier.background(Color.Gray)) {
            CustomTopAppBar("Title") {}
            SpacerHeight(height = 8.dp)
            CustomTopAppBar("Title")
            SpacerHeight(height = 8.dp)
            CustomTopAppBar(
                title = "Title",
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = ""
                        )
                    }
                }
            )
        }
    }
}