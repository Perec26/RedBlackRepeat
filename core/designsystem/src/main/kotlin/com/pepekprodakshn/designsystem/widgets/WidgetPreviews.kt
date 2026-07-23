package com.pepekprodakshn.designsystem.widgets

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview
import com.pepekprodakshn.designsystem.LANDSCAPE_DEVICE

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(device = LANDSCAPE_DEVICE)
@Preview(device = LANDSCAPE_DEVICE, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(device = "spec:width=600px,height=2340px,dpi=440")
annotation class WidgetPreviews
