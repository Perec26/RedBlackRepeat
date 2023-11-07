package com.pepekprodakshn.redblackrepeat.start.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pepekprodakshn.redblackrepeat.R
import com.pepekprodakshn.redblackrepeat.base.ui.LANDSCAPE_DEVICE
import com.pepekprodakshn.redblackrepeat.ui.theme.RedBlackRepeatTheme

@Composable
fun StartScreen(
    viewModel: StartViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsState().value

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = { viewModel.onEvent(StartEvent.ButtonClick) }) {
                Text(
                    modifier = Modifier.padding(32.dp),
                    text = stringResource(R.string.start_start),
                    fontSize = 36.sp
                )
            }
        }

        Text(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp),
            text = state.version
        )
    }
}

@Preview(showBackground = true, device = LANDSCAPE_DEVICE, showSystemUi = true)
@Composable
fun StartScreenPreview() {
    RedBlackRepeatTheme {
        StartScreen()
    }
}

@Preview(
    showBackground = true,
    device = LANDSCAPE_DEVICE,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun StartScreenPreviewDark() {
    RedBlackRepeatTheme {
        StartScreen ()
    }
}