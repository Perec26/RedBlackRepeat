package com.pepekprodakshn.start.ui

import app.cash.turbine.test
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

@OptIn(ExperimentalCoroutinesApi::class)
internal class StartViewModelTest : FreeSpec(
    {
        Dispatchers.setMain(Dispatchers.Unconfined)

        "Feature: StartViewModel" - {

            "Scenario: OnStartClick" - {

                "Given: StartViewModel" - {

                    val viewModel = StartViewModel()

                    "When: OnStartClick" - {

                        "Then: navigationEvent should be OnStartClick" - {
                            runTest {
                                viewModel.navigationEvent.test {
                                    viewModel.onEvent(StartEvent.OnStartClick)
                                    awaitItem() shouldBe StartNavigationEvent.OnStartClick
                                    cancelAndIgnoreRemainingEvents()
                                }
                            }
                        }
                    }
                }
            }
            "Scenario: OnSettingsClick" - {

                "Given: StartViewModel" - {

                    val viewModel = StartViewModel()

                    "When: OnSettingsClick" - {

                        "Then: navigationEvent should be OnSettingsClick" - {
                            runTest {
                                viewModel.navigationEvent.test {
                                    viewModel.onEvent(StartEvent.OnSettingsClick)
                                    awaitItem() shouldBe StartNavigationEvent.OnSettingsClick
                                    cancelAndIgnoreRemainingEvents()
                                }
                            }
                        }
                    }
                }
            }
        }
    },
)
