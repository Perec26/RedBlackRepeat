package com.pepekprodakshn.playerlist.domain

import com.pepekprodakshn.playerlist.domain.model.ValidationResult
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

internal class ValidateNameUseCaseTest : FreeSpec(
    {
        "Feature: ValidateNameUseCase" - {

            "Scenario: Validate name" - {

                "Given: ValidateNameUseCase" - {
                    val useCase = ValidateNameUseCase()

                    "When: Execute with valid name" - {
                        val result = useCase.execute("test")

                        "Then: Result should be Success" {
                            result shouldBe ValidationResult.Success
                        }
                    }

                    "When: Execute with empty name" - {
                        val result = useCase.execute("")

                        "Then: Result should be Error" {
                            result shouldBe ValidationResult.Error
                        }
                    }
                    "When: Execute with space name" - {
                        val result = useCase.execute(" ")

                        "Then: Result should be Error" {
                            result shouldBe ValidationResult.Error
                        }
                    }
                }
            }
        }
    },
)
