package com.pepekprodakshn.frame.ui

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import app.cash.turbine.test
import com.pepekprodakshn.frame.domain.GetPlayerUseCase
import com.pepekprodakshn.frame.ui.model.BallUI
import com.pepekprodakshn.frame.ui.navigation.Frame
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.every
import io.mockk.just
import io.mockk.mockkStatic
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

@OptIn(ExperimentalCoroutinesApi::class)
internal class FrameViewModelTest : FreeSpec(
    {
        Dispatchers.setMain(Dispatchers.Unconfined)

        fun getGetPlayerUseCase(): GetPlayerUseCase {
            val getPlayerUseCase = testGetPlayerUseCase
            coEvery { getPlayerUseCase.execute(1) } returns testFirstPlayerUI
            coEvery { getPlayerUseCase.execute(2) } returns testSecondPlayerUI
            return getPlayerUseCase
        }

        "Feature: FrameViewModel" - {

            mockkStatic("androidx.navigation.SavedStateHandleKt")
            every { any<SavedStateHandle>().toRoute<Frame>() } returns testFrame

            "Scenario: init" - {

                "Given: getPlayerUseCase" - {
                    val getPlayerUseCase = getGetPlayerUseCase()

                    "When: players ids are passed" - {
                        val viewModel = testFrameViewModel(
                            getPlayerUseCase = getPlayerUseCase,
                        )

                        "Then: players are set" {
                            with(viewModel.viewState) {
                                firstPlayerUI shouldBe testFirstPlayerUI
                                secondPlayerUI shouldBe testSecondPlayerUI
                            }
                        }
                    }
                }
            }

            "Scenario: onBallClick" - {

                "Given: FrameViewModel" - {

                    val potBallUseCase = testPotBallUseCase
                    val viewModel = testFrameViewModel(
                        potBallUseCase = potBallUseCase,
                    )

                    "When: ball is clicked" - {
                        viewModel.onEvent(FrameEvent.OnBallClick(BallUI.RED))

                        "Then: potBallUseCase should be called" {
                            verify { potBallUseCase.execute(BallUI.RED) }
                        }
                    }
                }
            }

            "Scenario: onSelectPlayer" - {

                "Given: FrameViewModel" - {
                    val endBreakUseCase = testEndBreakUseCase
                    every { endBreakUseCase.execute() } just Runs
                    val viewModel = testFrameViewModel(
                        endBreakUseCase = endBreakUseCase,
                    )
                    "When: second player is selected" - {

                        viewModel.onEvent(FrameEvent.OnSelectPlayer(false))

                        "Then: endBreakUseCase should be called" {
                            verify { endBreakUseCase.execute() }
                        }
                    }

                    "When: second player is not selected" - {
                        viewModel.onEvent(FrameEvent.OnSelectPlayer(false))

                        "Then: endBreakUseCase should not be called" {
                            verify(exactly = 1) { endBreakUseCase.execute() }
                        }
                    }
                }
            }

            "Scenario: onFoulPointClick" - {

                "Given: FrameViewModel" - {

                    val viewModel = testFrameViewModel()

                    "When: foul points are clicked" - {
                        viewModel.onEvent(FrameEvent.OnFoulPointClick(1))

                        "Then: foul points should be set" {
                            with(viewModel.viewState) {
                                foulUI.points shouldBe 1
                            }
                        }
                    }
                }
            }

            "Scenario: onFoulIsFreeBallClick" - {

                "Given: FrameViewModel" - {
                    val viewModel = testFrameViewModel()

                    "When: foul isFreeBall is clicked" - {
                        viewModel.onEvent(FrameEvent.OnFoulIsFreeBallClick)

                        "Then: foul isFreeBall should be set" {
                            with(viewModel.viewState) {
                                foulUI.isFreeBall shouldBe true
                                foulUI.isMiss shouldBe false
                            }
                        }
                    }

                    "When: foul isFreeBall is clicked one more time" - {
                        viewModel.onEvent(FrameEvent.OnFoulIsFreeBallClick)

                        "Then: foul isFreeBall should be set" {
                            with(viewModel.viewState) {
                                foulUI.isFreeBall shouldBe false
                                foulUI.isMiss shouldBe false
                            }
                        }
                    }
                }
            }

            "Scenario: onFoulIsMissClick" - {

                "Given: FrameViewModel" - {

                    val viewModel = testFrameViewModel()

                    "When: foul isMiss is clicked" - {
                        viewModel.onEvent(FrameEvent.OnFoulIsMissClick)

                        "Then: foul isMiss should be set" {
                            with(viewModel.viewState) {
                                foulUI.isMiss shouldBe true
                                foulUI.isFreeBall shouldBe false
                            }
                        }
                    }
                }
            }

            "Scenario: onFoulRemoveRedsClick" - {

                "Given: FrameViewModel" - {

                    val viewModel = testFrameViewModel()
                    repeat(4) { viewModel.onEvent(FrameEvent.OnFoulAddRedsClick) }

                    "When: foul removeReds is clicked" - {
                        viewModel.onEvent(FrameEvent.OnFoulRemoveRedsClick)

                        "Then: state should be set" {
                            with(viewModel.viewState) {
                                foulUI.removeReds shouldBe 3
                                foulUI.canAddReds shouldBe true
                            }
                        }
                    }
                }
            }

            "Scenario: OnFoulConfirmClick" - {

                "Given: FrameViewModel" - {

                    val foulUseCase = testFoulUseCase
                    every { foulUseCase.execute(any()) } just Runs
                    val viewModel = testFrameViewModel(
                        foulUseCase = foulUseCase,
                    )

                    "When: foulConfirm is clicked" - {
                        viewModel.onEvent(FrameEvent.OnFoulConfirmClick)

                        "Then: foulUseCase should be called" {
                            verify { foulUseCase.execute(any()) }
                        }

                        "And: foulBottomSheet should be closed" {
                            with(viewModel.viewState) {
                                showFoulBottomSheet shouldBe false
                            }
                        }
                    }
                }
            }

            "Scenario: onFoulAddRedsClick" - {

                "Given: FrameViewModel" - {

                    val viewModel = testFrameViewModel()

                    "When: foul addReds is clicked" - {
                        viewModel.onEvent(FrameEvent.OnFoulAddRedsClick)

                        "Then: state should be set" {
                            with(viewModel.viewState) {
                                foulUI.removeReds shouldBe 1
                                foulUI.canAddReds shouldBe true
                            }
                        }
                    }

                    "When: foul addReds is clicked and all reds are removed" - {
                        repeat(14) { viewModel.onEvent(FrameEvent.OnFoulAddRedsClick) }

                        "Then: state should be set" {
                            with(viewModel.viewState) {
                                foulUI.removeReds shouldBe 15
                                foulUI.canAddReds shouldBe false
                            }
                        }
                    }
                }
            }

            "Scenario: onFoulBottomSheetClosed" - {

                "Given: FrameViewModel" - {

                    val viewModel = testFrameViewModel()

                    "When: foulBottomSheet is closed" - {

                        viewModel.onEvent(FrameEvent.OnFoulBottomSheetClosed)

                        "Then: foulBottomSheet should not be shown" {
                            with(viewModel.viewState) {
                                showFoulBottomSheet shouldBe false
                            }
                        }
                    }
                }
            }

            "Scenario: OnAddRemoveRedsDialogClose" - {

                "Given: FrameViewModel" - {

                    val viewModel = testFrameViewModel()

                    "When: OnAddRemoveRedsDialogClose" - {

                        viewModel.onEvent(FrameEvent.OnAddRemoveRedsDialogClose)

                        "Then: showAddRemoveDialog should be false" {
                            with(viewModel.viewState) {
                                showAddRemoveDialog shouldBe false
                            }
                        }
                    }
                }
            }

            "Scenario: OnAddRedsDialogClick" - {

                "Given: FrameViewModel" - {
                    val addRedsUseCase = testAddRedsUseCase
                    every { addRedsUseCase.execute(any()) } just Runs
                    val viewModel = testFrameViewModel(
                        addRedsUseCase = addRedsUseCase,
                    )
                    "When: OnAddRemoveRedsDialogClick" - {
                        viewModel.onEvent(FrameEvent.OnAddRedsDialogClick)

                        "Then: addRedsUseCase should be called" {
                            verify { addRedsUseCase.execute(any()) }
                        }
                        "And: showAddRemoveDialog should be false" {
                            with(viewModel.viewState) {
                                showAddRemoveDialog shouldBe false
                            }
                        }
                    }
                }
            }

            "Scenario: OnRemoveRedsDialogClick" - {

                "Given: FrameViewModel" - {

                    val removeRedsUseCase = testRemoveRedsUseCase
                    every { removeRedsUseCase.execute(any()) } just Runs
                    val viewModel = testFrameViewModel(
                        removeRedsUseCase = removeRedsUseCase,
                    )
                    "When: OnRemoveRedsDialogClick" - {

                        viewModel.onEvent(FrameEvent.OnRemoveRedsDialogClick)

                        "Then: removeRedsUseCase should be called" {
                            verify { removeRedsUseCase.execute(any()) }
                        }
                        "And: showAddRemoveDialog should be false" {
                            with(viewModel.viewState) {
                                showAddRemoveDialog shouldBe false
                            }
                        }
                    }
                }
            }

            "Scenario: OnAddRemoveRedsPlusClick" - {

                "Given: FrameViewModel" - {

                    val viewModel = testFrameViewModel()

                    "When: OnAddRemoveRedsPlusClick" - {
                        viewModel.onEvent(FrameEvent.OnAddRemoveRedsPlusClick)

                        "Then: addRemoveDialogState should be updated" {
                            with(viewModel.viewState) {
                                addRemoveDialogState.redsCount shouldBe 1
                            }
                        }
                    }
                }
            }

            "Scenario: OnAddRemoveRedsMinusClick" - {

                "Given: FrameViewModel" - {

                    val viewModel = testFrameViewModel()

                    "When: OnAddRemoveRedsMinusClick" - {
                        repeat(5) { viewModel.onEvent(FrameEvent.OnAddRemoveRedsPlusClick) }
                        viewModel.onEvent(FrameEvent.OnAddRemoveRedsMinusClick)

                        "Then: addRemoveDialogState should be updated" {
                            with(viewModel.viewState) {
                                addRemoveDialogState.redsCount shouldBe 4
                            }
                        }
                    }
                }
            }

            "Scenario: OnOptionsElementsCounted" - {

                "Given: FrameViewModel" - {

                    val viewModel = testFrameViewModel()

                    "When: optionsElements are counted" - {

                        viewModel.onEvent(FrameEvent.OnOptionsElementsCounted(1))

                        "Then: optionsBottomSheet should be hidden" {
                            with(viewModel.viewState) {
                                optionElementsOnScreen shouldBe 1
                            }
                        }
                    }
                }
            }

            "Scenario: onFoulClick" - {

                "Given: FrameViewModel" - {

                    val viewModel = testFrameViewModel()

                    "When: foul is clicked" - {

                        viewModel.onEvent(FrameEvent.OnFoulClick)

                        "Then: foulBottomSheet should be shown" {
                            with(viewModel.viewState) {
                                showFoulBottomSheet shouldBe true
                                showOptionsBottomSheet shouldBe false
                                foulUI shouldBe testFoulUI
                            }
                        }
                    }
                }
            }

            "Scenario: onUndoClick" - {

                "Given: FrameViewModel" - {

                    val undoUseCase = testUndoUseCase
                    every { undoUseCase.execute() } just Runs
                    val viewModel = testFrameViewModel(
                        undoUseCase = undoUseCase,
                    )
                    "When: undo is clicked" - {

                        viewModel.onEvent(FrameEvent.OnUndoClick)

                        "Then: undoUseCase should be called" {
                            verify { undoUseCase.execute() }
                        }

                        "And: optionsBottomSheet should not be shown" {
                            with(viewModel.viewState) {
                                showOptionsBottomSheet shouldBe false
                            }
                        }
                    }
                }
            }

            "Scenario: OnAddRedsClick" - {

                "Given: FrameViewModel" - {

                    val viewModel = testFrameViewModel()

                    "When: OnAddRedsClick" - {
                        viewModel.onEvent(FrameEvent.OnAddRedsClick)

                        "Then: state should be updated" {
                            with(viewModel.viewState) {
                                addRemoveDialogState.isAdd shouldBe true
                                showAddRemoveDialog shouldBe true
                                showOptionsBottomSheet shouldBe false
                            }
                        }
                    }
                }
            }

            "Scenario: OnRemoveRedsClick" - {

                "Given: FrameViewModel" - {

                    val viewModel = testFrameViewModel()

                    "When: OnRemoveRedsClick" - {
                        viewModel.onEvent(FrameEvent.OnRemoveRedsClick)
                        "Then: state should be updated" {
                            with(viewModel.viewState) {
                                addRemoveDialogState.isAdd shouldBe false
                                showAddRemoveDialog shouldBe true
                                showOptionsBottomSheet shouldBe false
                            }
                        }
                    }
                }
            }

            "Scenario: onRestartClick" - {

                "Given: FrameViewModel" - {
                    val viewModel = testFrameViewModel()

                    "When: restartFrame is clicked" - {
                        viewModel.onEvent(FrameEvent.OnRestartClick)

                        "Then: restartFrameConfirmationDialog should be shown" {
                            with(viewModel.viewState) {
                                showOptionsBottomSheet shouldBe false
                                showRestartFrameConfirmationDialog shouldBe true
                            }
                        }
                    }
                }
            }

            "Scenario: OnMoreClick" - {

                "Given: FrameViewModel" - {

                    val viewModel = testFrameViewModel()

                    "When: more is clicked" - {
                        viewModel.onEvent(FrameEvent.OnMoreClick)

                        "Then: optionsBottomSheet should be shown" {
                            with(viewModel.viewState) {
                                showOptionsBottomSheet shouldBe true
                            }
                        }
                    }
                }
            }

            "Scenario: OnFrameOptionsBottomSheetClose" - {

                "Given: FrameViewModel" - {

                    val viewModel = testFrameViewModel()

                    "When: OnFrameOptionsBottomSheetClose" - {

                        viewModel.onEvent(FrameEvent.OnFrameOptionsBottomSheetClose)

                        "Then: showOptionsBottomSheet should be false" {

                            with(viewModel.viewState) {
                                showOptionsBottomSheet shouldBe false
                            }
                        }
                    }
                }
            }

            "Scenario: onFinishClick" - {

                "Given: FrameViewModel" - {

                    val viewModel = testFrameViewModel()

                    "When: finishFrame is clicked" - {

                        "Then: navigationEvent should be OnBackPressed" {
                            runTest {
                                viewModel.navigationEvent.test {
                                    viewModel.onEvent(FrameEvent.OnFinishClick)
                                    viewModel.viewState.showOptionsBottomSheet shouldBe false
                                    awaitItem() shouldBe FrameNavigationEvent.OnBackPressed
                                    cancelAndIgnoreRemainingEvents()
                                }
                            }
                        }
                    }
                }
            }

            "Scenario: OnRestartFrameConfirmationClosed" - {

                "Given: FrameViewModel" - {

                    val viewModel = testFrameViewModel()

                    "When: OnRestartFrameConfirmationClosed" - {

                        viewModel.onEvent(FrameEvent.OnRestartFrameConfirmationClosed)

                        "Then: showRestartFrameConfirmationDialog should be false" {
                            with(viewModel.viewState) {
                                showRestartFrameConfirmationDialog shouldBe false
                            }
                        }
                    }
                }
            }

            "Scenario: OnRestartConfirm" - {

                "Given: FrameViewModel" - {

                    val restartUseCase = testRestartUseCase
                    val viewModel = testFrameViewModel(
                        restartUseCase = restartUseCase,
                    )

                    "When: restartConfirm is clicked" - {
                        viewModel.onEvent(FrameEvent.OnRestartConfirm)

                        "Then: restartFrameConfirmationDialog should be hidden" {
                            with(viewModel.viewState) {
                                showRestartFrameConfirmationDialog shouldBe false
                            }
                        }

                        "And: restartUseCase should be called" {
                            verify { restartUseCase.execute() }
                        }
                    }
                }
            }
        }
    },
)
