package com.pepekprodakshn.frame.domain.mapper

import com.pepekprodakshn.frame.ui.model.BallUI
import com.pepekprodakshn.table.model.Ball
import com.pepekprodakshn.table.model.FrameBreak
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

internal class TableStateMapper() : FreeSpec(
    {
        "Scenario: mapping TableState" - {

            "Given: TableState" - {
                val tableState = testTableState

                "When: start table state mapped to UI" - {
                    val ui = tableState.toUI()

                    "Then: UI should be correct" {
                        with(ui) {
                            firstPlayerPoints shouldBe 0
                            secondPlayerPoints shouldBe 0
                            ballState.redsCount shouldBe 15
                            ballState.lowestPriceBall shouldBe BallUI.RED
                            ballState.nextIsColor shouldBe false
                            ballState.isFreeBall shouldBe false
                            breakUI shouldBe null
                            isFirstPlayerSelected shouldBe true
                            frameInfoUI.pointsOnTable shouldBe 147
                            frameInfoUI.snookersRequired shouldBe 0
                        }
                    }
                }

                "When: next is color" - {

                    val ui = tableState.copy(
                        frameBreak = FrameBreak(balls = listOf(Ball.RED)),
                        redsCount = 14,
                        nextIsColor = true,
                    ).toUI()

                    "Then: UI should be correct" {
                        with(ui) {
                            frameInfoUI.pointsOnTable shouldBe 146
                        }
                    }
                }
                "When: no reds on table" - {

                    val ui = testTableState.copy(
                        lowestValueBall = Ball.YELLOW,
                        redsCount = 0,
                    ).toUI()

                    "Then: UI should be correct" {
                        with(ui) {
                            ballState.lowestPriceBall shouldBe BallUI.YELLOW
                            ballState.redsCount shouldBe 0
                            frameInfoUI.pointsOnTable shouldBe 27
                        }
                    }
                }

                "When: have color before final" - {

                    val ui = testTableState.copy(
                        frameBreak = FrameBreak(balls = listOf(Ball.RED)),
                        redsCount = 0,
                        nextIsColor = true,
                    ).toUI()

                    "Then: UI should be correct" {
                        with(ui) {
                            ballState.redsCount shouldBe 0
                            frameInfoUI.pointsOnTable shouldBe 34
                        }
                    }
                }

                "When: snookers Requared and lowest value ball is red" - {
                    val ui = testTableState.copy(
                        firstPlayerPoints = 100,
                        secondPlayerPoints = 146,
                        redsCount = 1,
                    ).toUI()

                    "Then: UI should be correct" {
                        with(ui) {
                            frameInfoUI.snookersRequired shouldBe 3
                        }
                    }
                }

                "When: snookers Requared and lowest value ball is blue" - {
                    val ui = testTableState.copy(
                        firstPlayerPoints = 100,
                        secondPlayerPoints = 128,
                        redsCount = 0,
                        lowestValueBall = Ball.BLUE,
                    ).toUI()

                    "Then: UI should be correct" {
                        with(ui) {
                            frameInfoUI.snookersRequired shouldBe 2
                        }
                    }
                }
            }
        }
    },
)
