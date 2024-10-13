package com.pepekprodakshn.start.ui

import com.pepekprodakshn.navigation.SharedRouter
import io.kotest.core.spec.style.FreeSpec
import io.mockk.mockk
import io.mockk.verify

internal class StartViewModelTest : FreeSpec(
    {
        "Feature: StartViewModel" - {

            "Scenario: Button click" - {

                "Given: StartViewModel" - {
                    val sharedRouter = mockk<SharedRouter>(relaxed = true)
                    val viewModel = StartViewModel(sharedRouter = sharedRouter)

                    "When: Button click" - {
                        viewModel.onEvent(StartEvent.ButtonClick)

                        "Then: sharedRouter.navigateToPlayerList() should be called" {
                            verify { sharedRouter.navigateToPlayerList() }
                        }
                    }
                }
            }
        }
    },
)