package com.pepekprodakshn.frame.data

import com.pepekprodakshn.table.model.Ball
import com.pepekprodakshn.table.model.FrameBreak
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

internal class TableStateCalculatorTest :
    FreeSpec(
        {
            "Feature: TableStateCalculator" - {

                "Scenario: remove reds" - {

                    "Given: TableStateCalculator" - {

                        val calculator = TableStateCalculator()

                        "When: reds removed" - {
                            val actions = listOf(FrameActionsDTO.RemoveReds(1))
                            val state = calculator.calculateState(actions)

                            "Then: state should be correct" {
                                with(state) {
                                    redsCount shouldBe 14
                                }
                            }
                        }

                        "When: all reds removed" - {

                            val actions = listOf(FrameActionsDTO.RemoveReds(15))
                            val state = calculator.calculateState(actions)

                            "Then: state should be correct" {
                                with(state) {
                                    redsCount shouldBe 0
                                    lowestValueBall shouldBe Ball.YELLOW
                                }
                            }
                        }
                    }
                }

                "Scenario: break ended" - {

                    "Given: TableStateCalculator" - {

                        val calculator = TableStateCalculator()

                        "When: first player break ended" - {
                            val actions = listOf(FrameActionsDTO.BreakEnded)
                            val state = calculator.calculateState(actions)

                            "Then: state should be correct" {

                                with(state) {
                                    nextIsColor shouldBe false
                                    lowestValueBall shouldBe Ball.RED
                                    frameBreak shouldBe null
                                    isFirstPlayerSelected shouldBe false
                                }
                            }
                        }

                        "When: second player break ended" - {
                            val actions =
                                listOf(FrameActionsDTO.BreakEnded, FrameActionsDTO.BreakEnded)
                            val state = calculator.calculateState(actions)

                            "Then: state should be correct" {
                                with(state) {
                                    nextIsColor shouldBe false
                                    lowestValueBall shouldBe Ball.RED
                                    frameBreak shouldBe null
                                    isFirstPlayerSelected shouldBe true
                                }
                            }
                        }
                    }
                }

                "Scenario: foul" - {

                    "Given: TableStateCalculator" - {

                        val calculator = TableStateCalculator()

                        "When: first player foul" - {
                            val actions = listOf(FrameActionsDTO.Fouled(testFoul))
                            val state = calculator.calculateState(actions)

                            "Then: state should be correct" {
                                with(state) {
                                    frameBreak shouldBe null
                                    nextIsColor shouldBe false
                                    lowestValueBall shouldBe Ball.RED
                                    isFirstPlayerSelected shouldBe false
                                    firstPlayerPoints shouldBe 0
                                    secondPlayerPoints shouldBe 4
                                    redsCount shouldBe 15
                                }
                            }
                        }

                        "When: second player foul" - {
                            val actions =
                                listOf(FrameActionsDTO.BreakEnded, FrameActionsDTO.Fouled(testFoul))
                            val state = calculator.calculateState(actions)
                            "Then: state should be correct" {
                                with(state) {
                                    frameBreak shouldBe null
                                    nextIsColor shouldBe false
                                    lowestValueBall shouldBe Ball.RED
                                    isFirstPlayerSelected shouldBe true
                                    firstPlayerPoints shouldBe 4
                                    secondPlayerPoints shouldBe 0
                                    redsCount shouldBe 15
                                }
                            }
                        }

                        "When: player foul with miss" - {
                            val actions =
                                listOf(FrameActionsDTO.Fouled(testFoul.copy(isMiss = true)))
                            val state = calculator.calculateState(actions)

                            "Then: state should be correct" {
                                with(state) {
                                    frameBreak shouldBe null
                                    nextIsColor shouldBe false
                                    lowestValueBall shouldBe Ball.RED
                                    isFirstPlayerSelected shouldBe true
                                    firstPlayerPoints shouldBe 0
                                    secondPlayerPoints shouldBe 4
                                    redsCount shouldBe 15
                                }
                            }
                        }

                        "When: player foul with miss and nex is color" - {
                            val actions = listOf(
                                FrameActionsDTO.BallPotted(Ball.RED),
                                FrameActionsDTO.Fouled(testFoul.copy(isMiss = true)),
                            )
                            val state = calculator.calculateState(actions)

                            "Then: state should be correct" {
                                with(state) {
                                    frameBreak shouldBe null
                                    nextIsColor shouldBe true
                                    lowestValueBall shouldBe Ball.RED
                                    isFirstPlayerSelected shouldBe true
                                    firstPlayerPoints shouldBe 1
                                    secondPlayerPoints shouldBe 4
                                    redsCount shouldBe 14
                                }
                            }
                        }

                        "When: player foul in prefinal" - {
                            val actions = listOf(
                                FrameActionsDTO.RemoveReds(14),
                                FrameActionsDTO.BallPotted(Ball.RED),
                                FrameActionsDTO.Fouled(testFoul),
                            )
                            val state = calculator.calculateState(actions)

                            "Then: state should be correct" {
                                with(state) {
                                    frameBreak shouldBe null
                                    nextIsColor shouldBe false
                                    lowestValueBall shouldBe Ball.YELLOW
                                    isFirstPlayerSelected shouldBe false
                                    firstPlayerPoints shouldBe 1
                                    secondPlayerPoints shouldBe 4
                                    redsCount shouldBe 0
                                }
                            }
                        }

                        "When: player foul with free ball" - {
                            val actions = listOf(
                                FrameActionsDTO.Fouled(testFoul.copy(isFreeBall = true)),
                            )

                            val state = calculator.calculateState(actions)
                            "Then: state should be correct" {
                                with(state) {
                                    frameBreak shouldBe FrameBreak(isFreeBall = true)
                                    nextIsColor shouldBe false
                                    lowestValueBall shouldBe Ball.RED
                                    isFirstPlayerSelected shouldBe false
                                    firstPlayerPoints shouldBe 0
                                    secondPlayerPoints shouldBe 4
                                    redsCount shouldBe 15
                                }
                            }
                        }
                    }
                }

                "Scenario: pot ball" - {

                    "Given: TableStateCalculator" - {

                        val calculator = TableStateCalculator()

                        "When: red ball potted" - {
                            val actions = listOf(FrameActionsDTO.BallPotted(Ball.RED))

                            val state = calculator.calculateState(actions)

                            "Then: state should be correct" {
                                with(state) {
                                    redsCount shouldBe 14
                                    nextIsColor shouldBe true
                                    frameBreak shouldBe FrameBreak(listOf(Ball.RED))
                                    lowestValueBall shouldBe Ball.RED
                                    firstPlayerPoints shouldBe 1
                                    secondPlayerPoints shouldBe 0
                                }
                            }
                        }

                        "When: free ball potted when reds on table" - {
                            val actions = listOf(
                                FrameActionsDTO.Fouled(testFoul.copy(isFreeBall = true)),
                                FrameActionsDTO.BallPotted(Ball.BLACK),
                            )

                            val state = calculator.calculateState(actions)

                            "Then: state should be correct" {
                                with(state) {
                                    frameBreak shouldBe FrameBreak(
                                        isFreeBall = false,
                                        freeBallScore = 1,
                                    )
                                    nextIsColor shouldBe true
                                    firstPlayerPoints shouldBe 0
                                    secondPlayerPoints shouldBe 5
                                }
                            }
                        }

                        "When: free ball potted when no reds on table" - {
                            val actions = listOf(
                                FrameActionsDTO.RemoveReds(15),
                                FrameActionsDTO.Fouled(testFoul.copy(isFreeBall = true)),
                                FrameActionsDTO.BallPotted(Ball.BLACK),
                            )
                            val state = calculator.calculateState(actions)

                            "Then: state should be correct" {
                                with(state) {
                                    frameBreak shouldBe FrameBreak(
                                        isFreeBall = false,
                                        freeBallScore = 2,
                                    )
                                    nextIsColor shouldBe false
                                    firstPlayerPoints shouldBe 0
                                    secondPlayerPoints shouldBe 6
                                }
                            }
                        }

                        "When: color ball is potted" - {
                            val actions = listOf(
                                FrameActionsDTO.BallPotted(Ball.RED),
                                FrameActionsDTO.BallPotted(Ball.BLACK),
                            )
                            val state = calculator.calculateState(actions)

                            "Then: state should be correct" {
                                with(state) {
                                    redsCount shouldBe 14
                                    nextIsColor shouldBe false
                                    frameBreak shouldBe FrameBreak(listOf(Ball.RED, Ball.BLACK))
                                    lowestValueBall shouldBe Ball.RED
                                    firstPlayerPoints shouldBe 8
                                    secondPlayerPoints shouldBe 0
                                }
                            }
                        }

                        "When: last color ball before final" - {
                            val actions = listOf(
                                FrameActionsDTO.RemoveReds(14),
                                FrameActionsDTO.BallPotted(Ball.RED),
                                FrameActionsDTO.BallPotted(Ball.BLACK),
                            )
                            val state = calculator.calculateState(actions)

                            "Then: state should be correct" {
                                with(state) {
                                    redsCount shouldBe 0
                                    nextIsColor shouldBe false
                                    frameBreak shouldBe FrameBreak(listOf(Ball.RED, Ball.BLACK))
                                    lowestValueBall shouldBe Ball.YELLOW
                                    firstPlayerPoints shouldBe 8
                                    secondPlayerPoints shouldBe 0
                                }
                            }
                        }

                        "When: last ball potted and it is no draw" - {
                            val actions = listOf(
                                FrameActionsDTO.RemoveReds(15),
                                FrameActionsDTO.BallPotted(Ball.YELLOW),
                                FrameActionsDTO.BallPotted(Ball.GREEN),
                                FrameActionsDTO.BallPotted(Ball.BROWN),
                                FrameActionsDTO.BallPotted(Ball.BLUE),
                                FrameActionsDTO.BallPotted(Ball.PINK),
                                FrameActionsDTO.BallPotted(Ball.BLACK),
                            )
                            val state = calculator.calculateState(actions)

                            "Then: state should be correct" {
                                with(state) {
                                    redsCount shouldBe 0
                                    nextIsColor shouldBe false
                                    frameBreak shouldBe testBreakAllColors
                                    lowestValueBall shouldBe Ball.RED
                                    firstPlayerPoints shouldBe 27
                                    secondPlayerPoints shouldBe 0
                                }
                            }
                        }

                        "When: last ball potted and it is draw" - {

                            val actions = listOf(
                                FrameActionsDTO.BreakEnded,
                                FrameActionsDTO.BallPotted(Ball.RED),
                                FrameActionsDTO.BallPotted(Ball.BLACK),
                                FrameActionsDTO.BallPotted(Ball.RED),
                                FrameActionsDTO.BallPotted(Ball.BLACK),
                                FrameActionsDTO.BallPotted(Ball.RED),
                                FrameActionsDTO.BallPotted(Ball.BLACK),
                                FrameActionsDTO.BallPotted(Ball.RED),
                                FrameActionsDTO.BallPotted(Ball.YELLOW),
                                FrameActionsDTO.BreakEnded,
                                FrameActionsDTO.RemoveReds(11),
                                FrameActionsDTO.BallPotted(Ball.YELLOW),
                                FrameActionsDTO.BallPotted(Ball.GREEN),
                                FrameActionsDTO.BallPotted(Ball.BROWN),
                                FrameActionsDTO.BallPotted(Ball.BLUE),
                                FrameActionsDTO.BallPotted(Ball.PINK),
                                FrameActionsDTO.BallPotted(Ball.BLACK),
                            )

                            val state = calculator.calculateState(actions)

                            "Then: state should be correct" {
                                with(state) {
                                    redsCount shouldBe 0
                                    nextIsColor shouldBe false
                                    frameBreak shouldBe testBreakAllColors
                                    lowestValueBall shouldBe Ball.BLACK
                                    firstPlayerPoints shouldBe 27
                                    secondPlayerPoints shouldBe 27
                                }
                            }
                        }
                    }
                }
            }
        },
    )
