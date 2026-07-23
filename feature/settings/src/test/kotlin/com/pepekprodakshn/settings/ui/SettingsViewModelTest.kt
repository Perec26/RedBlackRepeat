package com.pepekprodakshn.settings.ui

import app.cash.turbine.test
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

@OptIn(ExperimentalCoroutinesApi::class)
internal class SettingsViewModelTest :
    FreeSpec(
        {
            "Feature: SettingsViewModel" - {

                val testDispatcher = StandardTestDispatcher()
                val getUseSystemThemeUseCase = testGetUseSystemThemeUseCase
                val getUseDarkThemeUseCase = testGetUseDarkThemeUseCase

                beforeSpec { Dispatchers.setMain(testDispatcher) }
                afterSpec { Dispatchers.resetMain() }

                beforeTest {
                    coEvery { getUseSystemThemeUseCase.execute() } returns true
                    coEvery { getUseDarkThemeUseCase.execute() } returns true
                }

                "Scenario: Initial state" - {

                    "Given: GetUseSystemThemeUseCase and GetUseDarkThemeUseCase" - {

                        "When: Initial state" - {

                            val viewModel = testViewModel(
                                getUseSystemThemeUseCase = getUseSystemThemeUseCase,
                                getUseDarkThemeUseCase = getUseDarkThemeUseCase,
                            )

                            "Then: state should be correct" {
                                with(viewModel.state.value) {
                                    useSystemTheme shouldBe true
                                    useDarkTheme shouldBe true
                                    useDarkThemeSwitchEnabled shouldBe false
                                }
                            }
                        }
                    }
                }

                "Scenario: OnUseSystemThemeClick" - {

                    "Given: UpdateUseSystemThemeUseCase" - {
                        val useCase = testUpdateUseSystemThemeUseCase
                        val viewModel = testViewModel(
                            getUseSystemThemeUseCase = getUseSystemThemeUseCase,
                            getUseDarkThemeUseCase = getUseDarkThemeUseCase,
                            updateUseSystemThemeUseCase = useCase,
                        )

                        "When: OnUseSystemThemeClick" - {

                            viewModel.onEvent(SettingsEvent.OnUseSystemThemeClick)
                            testDispatcher.scheduler.advanceUntilIdle()

                            "Then: updateUseSystemThemeUseCase should be called" {
                                with(viewModel.state.value) {
                                    useSystemTheme shouldBe false
                                    useDarkThemeSwitchEnabled shouldBe true
                                }
                            }

                            "And: useCase should be called" {
                                coVerify { useCase.execute(false) }
                            }
                        }
                    }
                }

                "Scenario: OnUseDarkThemeClick" - {

                    "Given: UpdateUseDarkThemeUseCase" - {
                        val useCase = testUpdateUseDarkThemeUseCase
                        val viewModel = testViewModel(
                            getUseSystemThemeUseCase = getUseSystemThemeUseCase,
                            getUseDarkThemeUseCase = getUseDarkThemeUseCase,
                            updateUseDarkThemeUseCase = useCase,
                        )

                        "When: OnUseDarkThemeClick" - {

                            viewModel.onEvent(SettingsEvent.OnUseDarkThemeClick)
                            testDispatcher.scheduler.advanceUntilIdle()

                            "Then: useDarkTheme should be changed" {
                                with(viewModel.state.value) {
                                    useDarkTheme shouldBe false
                                }
                            }

                            "And: useCase should be called" {
                                coVerify { useCase.execute(false) }
                            }
                        }
                    }
                }

                "Scenario: OnBackPressed" - {

                    "Given: SettingsViewModel" - {

                        val viewModel = testViewModel()

                        "When: OnBackPressed" - {

                            "Then: navigationEvent should be OnBackPress" {
                                runTest {
                                    viewModel.navigationEvent.test {
                                        viewModel.onEvent(SettingsEvent.OnBackPressed)
                                        testDispatcher.scheduler.advanceUntilIdle()
                                        awaitItem() shouldBe SettingsNavigationEvent.OnBackPress
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
