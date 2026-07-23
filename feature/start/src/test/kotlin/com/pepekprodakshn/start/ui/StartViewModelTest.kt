package com.pepekprodakshn.start.ui

import app.cash.turbine.test
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

@OptIn(ExperimentalCoroutinesApi::class)
internal class StartViewModelTest :
    FreeSpec(
        {
            Dispatchers.setMain(Dispatchers.Unconfined)

            "Feature: StartViewModel" - {

                "Given: StartViewModel" - {

                    val viewModel = testStartViewModel()

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

                    "When: initial" - {
                        "Then: state should be initial" - {
                            viewModel.state.value.version shouldBe "0.0.1 (dev)"
                        }
                    }

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
        },
    )
