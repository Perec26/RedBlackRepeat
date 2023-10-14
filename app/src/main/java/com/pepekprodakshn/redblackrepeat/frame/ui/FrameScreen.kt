package com.pepekprodakshn.redblackrepeat.frame.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices.AUTOMOTIVE_1024p
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pepekprodakshn.redblackrepeat.base.ui.widgets.SpacerHeight
import com.pepekprodakshn.redblackrepeat.base.ui.widgets.SpacerWidth
import com.pepekprodakshn.redblackrepeat.frame.ui.model.BallVO
import com.pepekprodakshn.redblackrepeat.frame.ui.widgets.Ball
import com.pepekprodakshn.redblackrepeat.ui.theme.RedBlackRepeatTheme

@Composable
fun FrameScreen(
    viewModel: FrameViewModel = viewModel(),
    onNavigation: () -> Unit,
) {
    val state = viewModel.state.collectAsState().value
    Column {
        SpacerHeight(8)
        Balls(viewModel::onBallClick)
        SpacerHeight(8)
        Fouls(
            onFoulsClick = viewModel::onFoulCLick,
            onRemoveCLick = viewModel::onRemoveCLick,
        )
        SpacerHeight(height = 0, modifier = Modifier.weight(1f))
        Row {
            Row(
                modifier = Modifier
                    .clickable { viewModel.selectPlayer(SelectedPlayer.FIRST) }
                    .background(
                        color = state.firstPlayerColor,
                        shape = RoundedCornerShape(
                            topStart = 0.dp,
                            bottomStart = 0.dp,
                            topEnd = 32.dp,
                            bottomEnd = 32.dp
                        )
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SpacerWidth(width = 32)
                Text(text = state.firstPlayerVO.name, fontSize = 36.sp)
                SpacerWidth(width = 32)
                Text(text = state.firstPlayerPoints.toString(), fontSize = 54.sp)
                SpacerWidth(width = 32)

            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .clickable { viewModel.selectPlayer(SelectedPlayer.SECOND) }
                    .clipToBounds()
                    .background(
                        color = state.secondPlayerColor,
                        shape = RoundedCornerShape(
                            topStart = 32.dp,
                            bottomStart = 32.dp,
                            topEnd = 0.dp,
                            bottomEnd = 0.dp
                        )
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SpacerWidth(width = 32)
                Text(text = state.secondPlayerPoints.toString(), fontSize = 54.sp)
                SpacerWidth(width = 32)
                Text(text = state.secondPlayerVO.name, fontSize = 36.sp)
                SpacerWidth(width = 32)
            }
        }
        SpacerHeight(height = 16)
    }
}

@Composable
private fun Balls(onClick: (BallVO) -> Unit) {
    Row(
        modifier = Modifier
            .aspectRatio(5f)
    ) {
        BallVO.values().forEach {
            SpacerWidth(16)
            Ball(
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(1f)
                    .shadow(
                        elevation = 2.dp,
                        shape = RoundedCornerShape(100.dp)
                    )
                    .weight(1f),
                ball = it,
                onClick = onClick
            )
        }

        SpacerWidth(16)

    }
}

@Composable
private fun Fouls(
    onFoulsClick: (Int) -> Unit,
    onRemoveCLick: (Int) -> Unit
) {
    Row {
        SpacerWidth(8)
        Button(onClick = { onFoulsClick(4) }) { Text(text = "4", fontSize = 50.sp) }
        SpacerWidth(8)
        Button(onClick = { onFoulsClick(5) }) { Text(text = "5", fontSize = 50.sp) }
        SpacerWidth(8)
        Button(onClick = { onFoulsClick(6) }) { Text(text = "6", fontSize = 50.sp) }
        SpacerWidth(8)
        Button(onClick = { onFoulsClick(7) }) { Text(text = "7", fontSize = 50.sp) }

        SpacerWidth(8, modifier = Modifier.weight(1f))

        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            onClick = { onRemoveCLick(-1) }
        ) { Text(text = "-1", fontSize = 50.sp) }
        SpacerWidth(8)
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            onClick = { onRemoveCLick(-2) }) { Text(text = "-2", fontSize = 50.sp) }
        SpacerWidth(8)
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            onClick = { onRemoveCLick(-3) }) { Text(text = "-3", fontSize = 50.sp) }
        SpacerWidth(8)
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            onClick = { onRemoveCLick(-4) }) { Text(text = "-4", fontSize = 50.sp) }
        SpacerWidth(8)
    }
}

@Preview(showBackground = true, device = AUTOMOTIVE_1024p, showSystemUi = true,)
@Composable
fun FrameScreenPreview() {
    RedBlackRepeatTheme {
        FrameScreen() {}
    }
}