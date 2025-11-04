package com.finances.api.domain.usecases.createTransaction;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record CreateTransactionUseCaseDto(
        @NotNull(message = "Transaction value cannot be null")
        @Positive(message = "Transaction value must be greater than 0")
        float value,

        @NotNull(message = "Transaction type cannot be null")
        @Size(min = 1, max = 50, message = "Transaction type must be between 1 and 50 characters")
        String type,

        @NotNull(message = "From account ID cannot be null")
        UUID fromAccountId,

        @NotNull(message = "Target account ID cannot be null")
        UUID targetAccountId
) {}