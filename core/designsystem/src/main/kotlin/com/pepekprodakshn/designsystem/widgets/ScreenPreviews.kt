package com.pepekprodakshn.designsystem.widgets

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview
import com.pepekprodakshn.designsystem.LANDSCAPE_DEVICE
import com.pepekprodakshn.designsystem.LANDSCAPE_SMALL_DEVICE

@Preview(
    name = "Medium",
    group = "Target",
    showBackground = true,
)
@Preview(
    name = "Medium Dark",
    group = "Target",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Medium Landscape",
    group = "Target",
    showBackground = true,
    device = LANDSCAPE_DEVICE,
)
@Preview(
    name = "Small",
    group = "Small",
    showBackground = true,
    device = "id:Galaxy Nexus",
)
@Preview(
    name = "Medium big Font",
    group = "Big Font",
    showBackground = true,
    fontScale = 2f,
)
@Preview(
    name = "Small big Font",
    group = "Big Font",
    showBackground = true,
    device = "id:Galaxy Nexus",
    fontScale = 2f,
)
@Preview(
    name = "Small big Font Landscape",
    group = "Big Font",
    showBackground = true,
    device = LANDSCAPE_SMALL_DEVICE,
    fontScale = 2f,
)
annotation class ScreenPreviews
