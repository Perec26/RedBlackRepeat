package com.pepekprodakshn.redblackrepeat.frame.data

import com.pepekprodakshn.redblackrepeat.frame.data.model.finalTableStateWithFreeBall
import com.pepekprodakshn.redblackrepeat.frame.data.model.foul
import com.pepekprodakshn.redblackrepeat.frame.data.model.foulWithFreeBall
import com.pepekprodakshn.redblackrepeat.frame.data.model.foulWithMiss
import com.pepekprodakshn.redblackrepeat.frame.data.model.foulWithRemoveReds
import com.pepekprodakshn.redblackrepeat.frame.data.model.preFinalTableState
import com.pepekprodakshn.redblackrepeat.frame.data.model.startTableStateWithFreeBall
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class TableStateDTOTest : FreeSpec({

    "Feature: TableStateDTO" - {

        "Scenario: end of break" - {

            "Given: start TableStateDTO" - {
                var state = TableStateDTO()

                "When: red ball potted" - {
                    state = state.onBreakEnded()

                    "Then: state should be correct" {
                        with(state) {
                            nextIsColor shouldBe false
                            lowestPriceBall shouldBe BallDTO.RED
                            breakDTO shouldBe null
                            isFirstPlayerSelected shouldBe false
                        }
                    }
                }
            }

            "Given: prefinal TableStateDTO" - {
                var state = preFinalTableState

                "When: red ball potted" - {
                    state = state.onBreakEnded()

                    "Then: state should be correct" {
                        with(state) {
                            nextIsColor shouldBe false
                            lowestPriceBall shouldBe BallDTO.YELLOW
                            breakDTO shouldBe null
                            isFirstPlayerSelected shouldBe false
                        }
                    }
                }
            }
        }

        "Scenario: pot ball" - {

            "Given: Start TableStateDTO" - {

                var state = TableStateDTO()

                "When: red ball potted" - {
                    state = state.onBallPotted(BallDTO.RED)

                    "Then: state should be correct" {
                        with(state) {
                            redsCount shouldBe 14
                            nextIsColor shouldBe true
                            breakDTO?.balls shouldBe listOf(BallDTO.RED)
                            lowestPriceBall shouldBe BallDTO.RED
                            firstPlayerPoints shouldBe 1
                            secondPlayerPoints shouldBe 0
                        }
                    }
                }

                "When: color ball potted" - {
                    state = state.onBallPotted(BallDTO.BLACK)

                    "Then: state should be correct" {
                        with(state) {
                            redsCount shouldBe 14
                            nextIsColor shouldBe false
                            breakDTO?.balls shouldBe listOf(BallDTO.RED, BallDTO.BLACK)
                            lowestPriceBall shouldBe BallDTO.RED
                            firstPlayerPoints shouldBe 8
                            secondPlayerPoints shouldBe 0
                        }
                    }
                }

                "When: red ball potted by second player" - {
                    state = state.onBreakEnded()
                    state = state.onBallPotted(BallDTO.RED)

                    "Then: state should be correct" {
                        with(state) {
                            redsCount shouldBe 13
                            nextIsColor shouldBe true
                            breakDTO?.balls shouldBe listOf(BallDTO.RED)
                            lowestPriceBall shouldBe BallDTO.RED
                            firstPlayerPoints shouldBe 8
                            secondPlayerPoints shouldBe 1
                        }
                    }
                }

                "When: color ball potted by second player" - {
                    state = state.onBallPotted(BallDTO.BLACK)

                    "Then: state should be correct" {
                        with(state) {
                            redsCount shouldBe 13
                            nextIsColor shouldBe false
                            breakDTO?.balls shouldBe listOf(BallDTO.RED, BallDTO.BLACK)
                            lowestPriceBall shouldBe BallDTO.RED
                            firstPlayerPoints shouldBe 8
                            secondPlayerPoints shouldBe 8
                        }
                    }
                }
            }

            "Given: prefinal TableStateDTO" - {

                var state = preFinalTableState

                "When: color ball potted" - {
                    state = state.onBallPotted(BallDTO.BLACK)

                    "Then: state should be correct" {
                        with(state) {
                            redsCount shouldBe 0
                            nextIsColor shouldBe false
                            breakDTO?.balls shouldBe listOf(BallDTO.BLACK)
                            lowestPriceBall shouldBe BallDTO.YELLOW
                            firstPlayerPoints shouldBe 7
                            secondPlayerPoints shouldBe 0
                        }
                    }
                }
            }

            "Given: start TableStateDTO with free ball" - {

                var state = startTableStateWithFreeBall

                "When: color ball potted" - {
                    state = state.onBallPotted(BallDTO.BLACK)

                    "Then: state should be correct" {
                        with(state) {
                            redsCount shouldBe 15
                            nextIsColor shouldBe true
                            breakDTO?.balls shouldBe listOf()
                            breakDTO?.isFreeBall shouldBe false
                            breakDTO?.freeBallScore shouldBe 1
                            lowestPriceBall shouldBe BallDTO.RED
                            firstPlayerPoints shouldBe 1
                            secondPlayerPoints shouldBe 0
                        }
                    }
                }
            }

            "Given: final TableStateDTO with free ball" - {

                var state = finalTableStateWithFreeBall

                "When: color ball potted" - {
                    state = state.onBallPotted(BallDTO.BLACK)

                    "Then: state should be correct" {
                        with(state) {
                            redsCount shouldBe 0
                            nextIsColor shouldBe false
                            breakDTO?.balls shouldBe listOf()
                            breakDTO?.isFreeBall shouldBe false
                            breakDTO?.freeBallScore shouldBe 2
                            lowestPriceBall shouldBe BallDTO.YELLOW
                            firstPlayerPoints shouldBe 2
                            secondPlayerPoints shouldBe 0
                        }
                    }
                }
            }
        }

        "Scenario: foul" - {

            "Given: start TableStateDTO" - {
                var state = TableStateDTO()

                "When: regular foul" - {

                    state = state.onFoul(foul)

                    "Then: state should be correct" {
                        with(state) {
                            breakDTO shouldBe null
                            nextIsColor shouldBe false
                            lowestPriceBall shouldBe BallDTO.RED
                            isFirstPlayerSelected shouldBe false
                            firstPlayerPoints shouldBe 0
                            secondPlayerPoints shouldBe 4
                            redsCount shouldBe 15
                        }
                    }
                }

                "When: regular foul with miss" - {

                    state = state.onFoul(foulWithMiss)

                    "Then: state should be correct" {
                        with(state) {
                            breakDTO shouldBe null
                            nextIsColor shouldBe false
                            lowestPriceBall shouldBe BallDTO.RED
                            isFirstPlayerSelected shouldBe false
                            firstPlayerPoints shouldBe 4
                            secondPlayerPoints shouldBe 4
                            redsCount shouldBe 15
                        }
                    }
                }

                "When: regular foul with free ball" - {

                    state = state.onFoul(foulWithFreeBall)

                    "Then: state should be correct" {
                        with(state) {
                            breakDTO?.isFreeBall shouldBe true
                            nextIsColor shouldBe false
                            lowestPriceBall shouldBe BallDTO.RED
                            isFirstPlayerSelected shouldBe true
                            firstPlayerPoints shouldBe 8
                            secondPlayerPoints shouldBe 4
                            redsCount shouldBe 15
                        }
                    }
                }

                "When: regular foul with remove reds" - {
                    state = state.onFoul(foulWithRemoveReds)

                    "Then: state should be correct" {
                        with(state) {
                            breakDTO shouldBe null
                            nextIsColor shouldBe false
                            lowestPriceBall shouldBe BallDTO.RED
                            isFirstPlayerSelected shouldBe false
                            firstPlayerPoints shouldBe 8
                            secondPlayerPoints shouldBe 8
                            redsCount shouldBe 14
                        }
                    }
                }
            }
        }

        "Scenario: add reds" - {

            "Given: start TableStateDTO" - {
                var state = TableStateDTO(redsCount = 13)

                "When: addReds" - {

                    state = state.onAddReds(2)

                    "Then: should be 15 reds" {
                        state.redsCount shouldBe 15
                    }
                }
            }
        }

        "Scenario: remove reds" - {

            "Given: start TableStateDTO" - {
                var state = TableStateDTO(redsCount = 13)

                "When: addReds" - {

                    state = state.onRemoveReds(2)

                    "Then: should be 11 reds" {
                        state.redsCount shouldBe 11
                    }
                }
            }
        }
    }
})
