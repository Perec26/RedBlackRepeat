package com.pepekprodakshn.redblackrepeat.main.ui

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import com.pepekprodakshn.designsystem.theme.RedBlackRepeatTheme
import com.pepekprodakshn.redblackrepeat.navigation.RedBlackRepeatApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        setContent {
            val state = viewModel.state.collectAsState().value
            RedBlackRepeatTheme(
                useSystemTheme = state.useSystemTheme,
                useDarkTheme = state.useDarkTheme,
            ) {
                RedBlackRepeatApp()
            }
        }
    }
}