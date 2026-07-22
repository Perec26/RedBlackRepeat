package com.pepekprodakshn.playerlist.ui

import app.cash.turbine.test
import com.pepekprodakshn.playerlist.domain.model.ValidationResult
import com.pepekprodakshn.playerlist.ui.model.PlayerUI
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.mockk.coVerify
import io.mockk.every
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

@OptIn(ExperimentalCoroutinesApi::class)
internal class ChoosePlayerViewModelTest :
    FreeSpec(
        {
            Dispatchers.setMain(Dispatchers.Unconfined)

            "Feature: ChoosePlayerViewModel" - {

                "Scenario: OnBackPressed" - {

                    "Given: ChoosePlayerViewModel" - {
                        val viewModel = testViewModel()

                        "When: OnBackPressed" - {

                            "Then: navigationEvent should be OnBackPress" {
                                runTest {
                                    viewModel.navigationEvent.test {
                                        viewModel.onEvent(ChoosePlayerEvent.OnBackPressed)
                                        awaitItem() shouldBe ChoosePlayerNavigationEvent.OnBackPress
                                        cancelAndIgnoreRemainingEvents()
                                    }
                                }
                            }
                        }
                    }
                }

                "Scenario: OnAddPlayerClick" - {

                    "Given: ChoosePlayerViewModel" - {
                        val viewModel = testViewModel()

                        "When: OnAddPlayerClick" - {
                            viewModel.onEvent(ChoosePlayerEvent.OnAddPlayerClick)

                            "Then: showNewPlayerBottomSheet should be true" {
                                with(viewModel.state.value) {
                                    showNewPlayerBottomSheet shouldBe true
                                }
                            }
                        }
                    }
                }

                "Scenario: OnNewPlayerBottomSheetClosed" - {

                    "Given: ChoosePlayerViewModel" - {
                        val viewModel = testViewModel()

                        "When: OnNewPlayerBottomSheet" - {
                            viewModel.onEvent(ChoosePlayerEvent.OnNewPlayerBottomSheetClosed)

                            "Then: showNewPlayerBottomSheet should be false" {
                                with(viewModel.state.value) {
                                    showNewPlayerBottomSheet shouldBe false
                                }
                            }
                        }
                    }
                }

                "Scenario: OnNewPlayerDoneClick" - {

                    "Given: ChoosePlayerViewModel" - {
                        val validateNameUseCase = testValidateNameUseCase
                        val addPlayerUseCase = testAddPlayerUseCase
                        val viewModel = testViewModel(
                            validateNameUseCase = validateNameUseCase,
                            addPlayerUseCase = addPlayerUseCase,
                        )

                        "When: OnNewPlayerDoneClick and validation is successful" - {
                            every {
                                validateNameUseCase.execute(any())
                            } returns ValidationResult.Success
                            viewModel.onEvent(ChoosePlayerEvent.OnNewPlayerDoneClick)

                            "Then: isNewPlayerError should be false" {
                                with(viewModel.state.value) {
                                    isNewPlayerError shouldBe false
                                }
                            }

                            "And: addPlayerUseCase should be called" {
                                coVerify { addPlayerUseCase.execute(any()) }
                            }

                            "And: bottomSheet should be refreshed" {
                                with(viewModel.state.value) {
                                    showNewPlayerBottomSheet shouldBe false
                                    newPlayerName shouldBe ""
                                }
                            }
                        }

                        "When: OnNewPlayerDoneClick and validation is not successful" - {
                            every { validateNameUseCase.execute(any()) } returns
                                ValidationResult.Error
                            viewModel.onEvent(ChoosePlayerEvent.OnNewPlayerDoneClick)

                            "Then: isNewPlayerError should be true" {
                                with(viewModel.state.value) {
                                    isNewPlayerError shouldBe true
                                }
                            }
                        }
                    }
                }

                "Scenario: OnNameChanged" - {

                    "Given: ChoosePlayerViewModel" - {
                        val viewModel = testViewModel()

                        "When: OnNameChanged" - {
                            viewModel.onEvent(ChoosePlayerEvent.OnNameChanged("test"))

                            "Then: newPlayerName should be test and error should be hidden" {
                                with(viewModel.state.value) {
                                    newPlayerName shouldBe "test"
                                    isNewPlayerError shouldBe false
                                }
                            }
                        }
                    }
                }

                "Scenario: OnPlayerClick" - {

                    "Given: ChoosePlayerViewModel" - {
                        val viewModel = testViewModel()

                        "When: OnPlayerClick" - {
                            viewModel.onEvent(ChoosePlayerEvent.OnPlayerClick(PlayerUI(1, "test")))

                            "Then: player should be selected" - {
                                with(viewModel.state.value) {
                                    selectedPlayers.size shouldBe 1
                                }
                            }
                        }
                        "When: OnPlayerClick one more time" - {
                            viewModel.onEvent(ChoosePlayerEvent.OnPlayerClick(PlayerUI(1, "test")))

                            "Then: player should be selected" - {
                                with(viewModel.state.value) {
                                    selectedPlayers.size shouldBe 0
                                }
                            }
                        }
                    }
                }

                "Scenario: OnStartMatchClick" - {

                    "Given: ChoosePlayerViewModel" - {
                        val viewModel = testViewModel()

                        "When: OnStartMatchClick" - {

                            "Then: navigationEvent should be OnStartFrameClick" {
                                runTest {
                                    viewModel.navigationEvent.test {
                                        viewModel.onEvent(
                                            ChoosePlayerEvent.OnPlayerClick(firstPlayer),
                                        )
                                        viewModel.onEvent(
                                            ChoosePlayerEvent.OnPlayerClick(secondPlayer),
                                        )
                                        viewModel.onEvent(ChoosePlayerEvent.OnStartFrameClick)
                                        awaitItem() shouldBe
                                            ChoosePlayerNavigationEvent.OnStartFrameClick(
                                                firstPlayerId = firstPlayer.id,
                                                secondPlayerId = secondPlayer.id,
                                            )
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
