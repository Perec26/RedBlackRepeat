package com.pepekprodakshn.start.ui

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
import com.pepekprodakshn.designsystem.LANDSCAPE_DEVICE
import com.pepekprodakshn.designsystem.theme.RedBlackRepeatTheme
import com.pepekprodakshn.start.R

@Composable
internal fun StartScreen(
    viewModel: StartViewModel = hiltViewModel(),
    onStartClick: () -> Unit = {},
) {
    val state = viewModel.state.collectAsState().value

    StartScreenContent(
        state = state,
        onStartClick = onStartClick,
    )
}

@Composable
private fun StartScreenContent(
    state: StartUiState,
    onStartClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Button(onClick = onStartClick) {
                Text(
                    modifier = Modifier.padding(32.dp),
                    text = stringResource(R.string.start_start),
                    fontSize = 36.sp,
                )
            }
        }

        Text(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp),
            text = state.version,
        )
    }
}

@Preview(showBackground = true, device = LANDSCAPE_DEVICE, showSystemUi = true)
@Composable
private fun StartScreenPreview() {
    RedBlackRepeatTheme {
        StartScreenContent(StartUiState()) {}
    }
}

@Preview(
    showBackground = true,
    device = LANDSCAPE_DEVICE,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun StartScreenPreviewDark() {
    RedBlackRepeatTheme {
        StartScreenContent(StartUiState()) {}
    }
}